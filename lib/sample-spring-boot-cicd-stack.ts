import * as ec2 from "aws-cdk-lib/aws-ec2";
import * as ecs from "aws-cdk-lib/aws-ecs";
import * as elbv2 from "aws-cdk-lib/aws-elasticloadbalancingv2";
import * as log from "aws-cdk-lib/aws-logs";

import * as cdk from "aws-cdk-lib";
import { Construct } from "constructs";

export class SampleSpringBootCicdStack extends cdk.Stack {
  constructor(scope: Construct, id: string, props?: cdk.StackProps) {
    super(scope, id, props);

    // VPC
    const vpc = new ec2.Vpc(this, "Vpc", {
      subnetConfiguration: [
        {
          cidrMask: 24,
          name: "public",
          subnetType: ec2.SubnetType.PUBLIC,
        },
      ],
    });

    // SecurityGroup
    const securityGroupELB = new ec2.SecurityGroup(this, "SecurityGroupELB", {
      vpc,
    });
    securityGroupELB.addIngressRule(
      ec2.Peer.ipv4("0.0.0.0/0"),
      ec2.Port.tcp(80)
    );

    const securityGroupAPP = new ec2.SecurityGroup(this, "SecurityGroupAPP", {
      vpc,
    });

    // ALB
    const alb = new elbv2.ApplicationLoadBalancer(this, "ALB", {
      vpc,
      securityGroup: securityGroupELB,
      internetFacing: true,
    });
    const listenerHTTP = alb.addListener("ListenerHTTP", {
      port: 80,
    });

    // TargetGroup
    const targetGroup = new elbv2.ApplicationTargetGroup(this, "TG", {
      vpc: vpc,
      port: 8080,
      protocol: elbv2.ApplicationProtocol.HTTP,
      targetType: elbv2.TargetType.IP,
      healthCheck: {
        path: "/",
        healthyHttpCodes: "200",
      },
    });

    listenerHTTP.addTargetGroups("DefaultHTTPSResponse", {
      targetGroups: [targetGroup],
    });

    // ECS Cluster
    const cluster = new ecs.Cluster(this, "Cluster", {
      vpc,
    });

    // Fargate
    const fargateTaskDefinition = new ecs.FargateTaskDefinition(
      this,
      "TaskDef",
      {
        memoryLimitMiB: 1024,
        cpu: 512,
        runtimePlatform: {
          cpuArchitecture: ecs.CpuArchitecture.ARM64,
        },
      }
    );

    const container = fargateTaskDefinition.addContainer(
      "SampleSpringBootContainer",
      {
        image: ecs.ContainerImage.fromAsset("./app/"),
        logging: ecs.LogDrivers.awsLogs({
          streamPrefix: "sample-spring-boot-cicd",
          logRetention: log.RetentionDays.ONE_MONTH,
        }),
      }
    );

    container.addPortMappings({
      containerPort: 8080,
      hostPort: 8080,
    });

    const service = new ecs.FargateService(this, "Service", {
      cluster,
      taskDefinition: fargateTaskDefinition,
      desiredCount: 1,
      assignPublicIp: true,
      securityGroups: [securityGroupAPP],
    });
    service.attachToApplicationTargetGroup(targetGroup);
  }
}

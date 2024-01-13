package com.sample.sampleSpringBootCicd;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Value;

@SpringBootApplication
@RestController
public class Application {

  // application.propertiesの値を取得
  @Value("${application.name}")
  private String name;

  // application.propertiesの値を取得
  @Value("${application.version}")
  private String version;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @GetMapping("")
  public Config index() {

    // 現在の日時を取得
    LocalDateTime now = LocalDateTime.now();

    // 日時をフォーマット
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String formattedDateTime = now.format(formatter);

    return new Config(name, version, formattedDateTime);
  }

  static class Config {
    private String name;
    private String version;
    private String timestamp;

    public Config(String name, String version, String timestamp) {
      this.name = name;
      this.version = version;
      this.timestamp = timestamp;
    }

    public String getName() {
      return name;
    }

    public String getVersion() {
      return version;
    }

    public String getTimestamp() {
      return timestamp;
    }
  }
}

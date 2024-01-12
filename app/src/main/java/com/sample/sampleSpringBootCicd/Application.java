package com.sample.sampleSpringBootCicd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @GetMapping("")
  public Config index() {
    return new Config(1, "Hello, Sample Spring Boot CI/CD!");
  }

  static class Config {
    private int version;
    private String message;

    public Config(int version, String message) {
      this.version = version;
      this.message = message;
    }

    public int getVersion() {
      return version;
    }

    public String getMessage() {
      return message;
    }
  }
}

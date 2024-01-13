package com.sample.sampleSpringBootCicd;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    // 現在の日時を取得
    LocalDateTime now = LocalDateTime.now();

    // 日時をフォーマット
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String formattedDateTime = now.format(formatter);

    return new Config(4, "Hello, Sample Spring Boot!", formattedDateTime);
  }

  static class Config {
    private int version;
    private String message;
    private String timestamp;

    public Config(int version, String message, String timestamp) {
      this.version = version;
      this.message = message;
      this.timestamp = timestamp;
    }

    public int getVersion() {
      return version;
    }

    public String getMessage() {
      return message;
    }

    public String getTimestamp() {
      return timestamp;
    }
  }
}

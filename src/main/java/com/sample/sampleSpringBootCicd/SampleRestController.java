package com.sample.sampleSpringBootCicd;

@RestController
public class SampleRestController {

  @GetMapping("/json")
  public MyResponse getJson() {
    return new MyResponse("version", "1");
  }

  static class MyResponse {
    private String key;
    private String value;

    public MyResponse(String key, String value) {
      this.key = key;
      this.value = value;
    }

    public String getKey() {
      return key;
    }

    public String getValue() {
      return value;
    }
  }
}

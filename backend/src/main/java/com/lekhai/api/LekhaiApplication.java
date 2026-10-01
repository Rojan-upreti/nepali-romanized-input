// Spring Boot entry point that starts the Lekhai HTTP API.
package com.lekhai.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class LekhaiApplication extends SpringBootServletInitializer {
  public static void main(String[] args) { SpringApplication.run(LekhaiApplication.class, args); }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(LekhaiApplication.class);
  }
}

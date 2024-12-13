package com.fossm.contentservice;

import com.fossm.kafka.properties.TopicProperties;
import jakarta.annotation.PostConstruct;
import java.util.TimeZone;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableConfigurationProperties(TopicProperties.class)

// @ComponentScan(basePackages = {"com.codebotx.security", "com.fossm.contentservice" })

public class ContentServiceApplication {

  @PostConstruct
  public void init() {
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
  }

  public static void main(String[] args) {
    SpringApplication.run(ContentServiceApplication.class, args);
  }

}

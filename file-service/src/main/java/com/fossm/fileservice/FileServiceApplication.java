package com.fossm.fileservice;

import com.fossm.kafka.properties.TopicProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(TopicProperties.class)
public class FileServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(FileServiceApplication.class, args);
  }

}

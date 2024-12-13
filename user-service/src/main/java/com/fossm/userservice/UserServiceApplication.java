package com.fossm.userservice;

import com.fossm.kafka.properties.TopicProperties;
import jakarta.annotation.PostConstruct;
import java.util.TimeZone;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@SpringBootApplication
@EnableConfigurationProperties(TopicProperties.class)

// @EnableFeignClients(basePackages = "com.fossm.userservice.authclient")

@ComponentScan(basePackages = {"com.codebotx.security", "com.fossm.userservice" })
// @Import(WebSecurityConfig.class)
// @EnableJpaRepositories(basePackages = {"com.fossm.userservice.repository","com.codebotx.security.repository"})
// @EntityScan(basePackages = {"com.fossm.userservice.model","com.codebotx.security.model"})

public class UserServiceApplication {

  @PostConstruct
  public void init() {
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
  }

  public static void main(String[] args) {
    SpringApplication.run(UserServiceApplication.class, args);
  }

}

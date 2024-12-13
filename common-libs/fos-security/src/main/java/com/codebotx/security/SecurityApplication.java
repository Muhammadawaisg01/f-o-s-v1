package com.codebotx.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
// import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
// import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


// @EnableDiscoveryClient
@SpringBootApplication
// @EnableJpaRepositories(basePackages = "com.codebotx.security.repository") // Explicitly enable repositories
// @EntityScan(basePackages = "com.codebotx.security.data") // Explicitly scan for entities
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

}

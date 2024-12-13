package com.fossm.userservice.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration

// @EnableJpaRepositories(
//     basePackages = "com.fossm.userservice.repository",
//     repositoryImplementationPostfix = "Impl",
//     entityManagerFactoryRef = "userServiceEntityManagerFactory",
//     transactionManagerRef = "userServiceTransactionManager"
// )
public class ApplicationConfig {

}

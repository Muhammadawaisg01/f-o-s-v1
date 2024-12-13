package com.fossm.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
// import org.springframework.security.core.context.SecurityContext;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
@ComponentScan("com.fossm.swagger")
@OpenAPIDefinition(info = @Info(title = "FOS API Documentation", version = "1.0"))
@SecurityScheme(
    name = "Bearer Authentication",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    scheme = "bearer",
    description = "JWT token-based authentication",
    paramName = "Authorization")
public class SwaggerAutoConfiguration {


}

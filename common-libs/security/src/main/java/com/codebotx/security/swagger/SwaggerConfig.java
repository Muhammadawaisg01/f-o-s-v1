package com.codebotx.security.swagger;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.Info;
import io.swagger.v3.oas.models.OpenAPI;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.codebotx.security.controllers")) // Limit to your controllers
                .paths(PathSelectors.any()) // Expose all endpoints
                .build();
    }

    // @Bean
    // public OpenAPI customOpenAPI() {
    //     return new OpenAPI()
    //         .info(new Info().title("FOS Security API").version("1.0"));
    // }
}

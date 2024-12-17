
package com.codebotx.api_gateway.Config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    // @Bean
    // public RouteLocator routes(RouteLocatorBuilder builder) {
    //     return builder.routes()
    //         .route("example_route", r -> r.path("/test/**")
    //             .uri("http://localhost:8090"))
    //         .build();
    // }
}

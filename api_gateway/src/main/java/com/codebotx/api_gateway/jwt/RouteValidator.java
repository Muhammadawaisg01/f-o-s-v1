
package com.codebotx.api_gateway.jwt;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Predicate;


@Component
public class RouteValidator {

    public static final List<String> openApiEndpoints = List.of(
            "/api/auth/signin",
            "/api/auth/signup"
            // "/eureka"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));

}
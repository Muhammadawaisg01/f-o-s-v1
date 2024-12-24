package com.codebotx.api_gateway.jwt;


import com.codebotx.api_gateway.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;

    //    @Autowired
//    private RestTemplate template;
    
    @Autowired
    private JwtUtils jwtUtil;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
                //header contains token or not
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("missing authorization header");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                
                    try {
    //                    //REST call to AUTH service
    //                    template.getForObject("http://IDENTITY-SERVICE//validate?token" + authHeader, String.class);
                        jwtUtil.validateJwtToken(authHeader);
                        
                    } catch (Exception e) {
                        System.out.println("invalid access...!");
                        throw new RuntimeException("unauthorized access to application");
                    }
                }else{
                    throw new RuntimeException("Unauthenticated Access/No Token found");
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class Config {

    }
}
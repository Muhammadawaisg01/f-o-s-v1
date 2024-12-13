package com.fossm.contentservice.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages={"com.fossm.contentservice.client"})
public class FeignConfig {
//todo: error decoder
//todo: logger
}

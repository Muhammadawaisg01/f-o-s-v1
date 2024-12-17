package com.codebotx.api_gateway.TestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

    @GetMapping("/gateway/me")
    public String helloWorld(){
        return "Hi, I am in API_GATEWAY";
    }

}

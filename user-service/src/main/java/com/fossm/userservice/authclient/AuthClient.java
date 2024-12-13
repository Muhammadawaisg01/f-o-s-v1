// package com.fossm.userservice.authclient;

// import org.springframework.cloud.openfeign.FeignClient;
// // import org.springframework.cloud.openfeign.FeignContext;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Component;
// import org.springframework.web.bind.annotation.PostMapping;

// import com.fossm.userservice.dto.request.LoginRequest;
// import com.fossm.userservice.dto.request.SignupRequest;

// // import com.codebotx.security.data.payload.request.LoginRequest;
// // import com.codebotx.security.data.payload.request.SignupRequest;

// import io.swagger.v3.oas.annotations.parameters.RequestBody;

// @Component
// @FeignClient(name = "fos-security", url = "http://localhost:8082/security") 
// public interface AuthClient {

//     @PostMapping("/api/auth/signin")
//     ResponseEntity<?> signIn(@RequestBody LoginRequest request);

//     @PostMapping("/api/auth/signup")
//     ResponseEntity<?> signUp(@RequestBody SignupRequest newUser);

// }
    

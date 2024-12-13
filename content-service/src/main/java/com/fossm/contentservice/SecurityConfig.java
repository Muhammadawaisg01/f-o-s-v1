
package com.fossm.contentservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// import com.fossm.contentservice.jwt.AuthFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {


    // @Bean
    // public AuthFilter authenticationJwtTokenFilter() {
    //     return new AuthFilter();
    // }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        
            
        httpSecurity.csrf(csrf -> csrf.disable())       // Disable all security
            .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());


            // httpSecurity.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);


            return httpSecurity.build();

    }
}

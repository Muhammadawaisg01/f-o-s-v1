package com.codebotx.security.jwt;

import com.codebotx.security.exception.exception.ErrorMessage;
import com.codebotx.security.exception.exception.TokenValidationException;
import com.codebotx.security.service.UserDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String requestPath = request.getServletPath();


        System.out.println("HI I AM  REQUEST PATH IN FILTER CLASS      "+ requestPath);

        // Skip JWT validation for Swagger-related endpoints
        if (requestPath.startsWith("/swagger-ui") ||
                requestPath.equals("/v3/api-docs") ||
                requestPath.startsWith("/swagger-resources") ||
                requestPath.startsWith("/webjars") ||
                requestPath.equals("/api/auth/signin") ||
                requestPath.equals("/api/auth/signup")
            ){
                System.out.println("HELLO 01");
                filterChain.doFilter(request, response);
                System.out.println("HELLO 02");
                return;
            }
 
        try {
            String jwt = parseJwt(request);

            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                String username = jwtUtils.getUserNameFromJwtToken(jwt);

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails,
                                null,
                                userDetails.getAuthorities());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (TokenValidationException e) {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            ErrorMessage body = new ErrorMessage(
                    HttpStatus.UNAUTHORIZED.value(),
                    new Date(),
                    e.getMessage(),
                    String.format("uri=%s", request.getRequestURI())
            );

            final ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            mapper.setDateFormat(dateFormat);

            mapper.writeValue(response.getOutputStream(), body);
            System.out.println("HELLO 03");
            
            return;
        }

        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }

        return null;
    }


    // @Override
    // protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    //     String path = request.getServletPath();
    //     System.out.println("HELLO 04");
    //     return path.equals("/security/api/auth/signup"); // Skip authentication for this route
    // }

}

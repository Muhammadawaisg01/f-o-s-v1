// package com.fossm.contentservice.jwt;

// import java.io.IOException;
// import java.security.Key;
// import java.text.SimpleDateFormat;
// import java.util.Date;
// import java.util.Map;

// import org.springframework.security.core.AuthenticationException;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springdoc.api.ErrorMessage;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.MediaType;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
// import org.springframework.util.StringUtils;
// import org.springframework.web.filter.OncePerRequestFilter;

// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.fasterxml.jackson.databind.SerializationFeature;
// import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.ExpiredJwtException;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.MalformedJwtException;
// import io.jsonwebtoken.UnsupportedJwtException;
// import io.jsonwebtoken.io.Decoders;
// import io.jsonwebtoken.security.Keys;
// import io.jsonwebtoken.security.SignatureException;
// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;


// public class AuthFilter extends OncePerRequestFilter {


//     private static final Logger LOGGER = LoggerFactory.getLogger(AuthFilter.class);

//     @Value("${spring.app.jwtSecret}")
//     private String jwtSecret;

//     @Value("${spring.app.jwtExpirationMs}")
//     private int jwtExpirationMs;


//     @Override
//     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//             throws ServletException, IOException {
            

//                 String requestPath = request.getServletPath();

//                 System.out.println("HI I AM  REQUEST PATH IN FILTER CLASS IN CONTENT-SERVICE    "+ requestPath);

//             try {
//                 String jwt = parseJwt(request);             // extract token from request

//                 if (jwt != null && validateJwtToken(jwt)) 
//                 {
//                     Map<String,Object> userDetails = getUserDetailsFromJwtToken(jwt);
                    

//                     // UserDetails userDetails = userDetailsService.loadUserByUsername(username);

//                     UsernamePasswordAuthenticationToken authentication =
//                             new UsernamePasswordAuthenticationToken(userDetails,
//                                     null,
//                                     userDetails.getAuthorities());

//                     authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    
//                     SecurityContextHolder.getContext().setAuthentication(authentication);
//                 }
//         } catch (AuthenticationException e) {
//             response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//             response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

//             // ErrorMessage body = new ErrorMessage(
//             //         HttpStatus.UNAUTHORIZED.value(),
//             //         new Date(),
//             //         e.getMessage(),
//             //         String.format("uri=%s", request.getRequestURI())
//             // );

//             final ObjectMapper mapper = new ObjectMapper();
//             mapper.registerModule(new JavaTimeModule());
//             mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//             SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//             mapper.setDateFormat(dateFormat);

//             // mapper.writeValue(response.getOutputStream(), body);
//             System.out.println("HELLO 03");
            
//             return;
//         }

//         filterChain.doFilter(request, response);
                   
//     }

//     private String parseJwt(HttpServletRequest request) {
//         String headerAuth = request.getHeader("Authorization");

//         if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
//             return headerAuth.substring(7);
//         }
    
//         return null;
//     }


//     public boolean validateJwtToken(String authToken)  {
//         try {
//             Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
//             return true;
//         } catch (MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException |
//                  SignatureException e) {
//             LOGGER.error("Invalid JWT token: {}", e.getMessage());
//             // throw new AuthenticationException();
//         }
//         return false;
//     }

//     private Key key() {
//         return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
//     }

//     private Map<String, Object> getUserDetailsFromJwtToken(String token){

//         Claims claims =  Jwts.parserBuilder().setSigningKey(key()).build()
//                             .parseClaimsJws(token).getBody();
        
//         System.out.println("All Claims: " + claims);

//         // Extract custom claim
//         Map<String, Object> userDetails = (Map<String, Object>) claims.get("userDetails");
//         System.out.println("User Details: " + userDetails);
        
//         // Extract subject
//         String subject = claims.getSubject();
//         System.out.println("Subject: " + subject);
        
//         return userDetails;
//     }


// }

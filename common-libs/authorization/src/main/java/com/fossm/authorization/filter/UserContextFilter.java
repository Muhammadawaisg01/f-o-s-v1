// package com.fossm.authorization.filter;

// import com.fossm.authorization.context.UserContextHolder;

// import com.fasterxml.jackson.annotation.JsonProperty;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import java.io.IOException;
// import java.util.Base64;
// import java.util.UUID;
// import lombok.Data;
// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.http.HttpHeaders;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// @Component
// @RequiredArgsConstructor
// @Slf4j
// public class UserContextFilter extends OncePerRequestFilter {

//   private static final String BEARER = "Bearer";
//   private static final Base64.Decoder decoder = Base64.getDecoder();

//   private final UserContextHolder userContextHolder;

//   private final ObjectMapper objectMapper;

//   @Override
//   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//       throws IOException, ServletException {

//     var authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
//     if (authorizationHeader == null) {
//       filterChain.doFilter(request, response);
//     } else {
//       var jwt = authorizationHeader.replace(BEARER, "");
//       var chunks = jwt.split("\\.");
//       var payloadString = new String(decoder.decode(chunks[1]));
//       var payload = objectMapper.readValue(payloadString, JwtPayload.class);
//       log.debug("Received request with JWT: '{}'", payload);
//       userContextHolder.setId(payload.getSubject());
//       userContextHolder.setUsername(payload.getUsername());
//       try {
//         filterChain.doFilter(request, response);
//       } finally {
//         userContextHolder.remove();
//         log.debug("Removed user ID from context.");
//       }
//     }
//   }

//   @Data
//   private static class JwtPayload {

//     @JsonProperty("sub")
//     private UUID subject;
//     @JsonProperty("iss")
//     private String issuer;
//     @JsonProperty("client_id")
//     private String clientId;
//     @JsonProperty("origin_jti")
//     private String originJti;
//     @JsonProperty("event_id")
//     private String eventId;
//     @JsonProperty("token_use")
//     private String tokenUse;
//     private String scope;
//     @JsonProperty("auth_time")
//     private Long authenticatedAt;
//     @JsonProperty("exp")
//     private Long expiresAt;
//     @JsonProperty("iat")
//     private Long issuedAt;
//     @JsonProperty("jti")
//     private String jwtId;
//     private String username;
//   }
// }

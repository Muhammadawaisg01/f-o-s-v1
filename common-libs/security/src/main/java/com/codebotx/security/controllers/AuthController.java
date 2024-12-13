package com.codebotx.security.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.codebotx.security.data.ERole;
import com.codebotx.security.data.RefreshToken;
import com.codebotx.security.data.Role;
import com.codebotx.security.data.User;
import com.codebotx.security.data.payload.request.LoginRequest;
import com.codebotx.security.data.payload.request.SignupRequest;
import com.codebotx.security.data.payload.request.TokenRefreshRequest;
import com.codebotx.security.data.payload.response.JwtResponse;
import com.codebotx.security.data.payload.response.MessageResponse;
import com.codebotx.security.data.payload.response.TokenRefreshResponse;
import com.codebotx.security.exception.exception.SignInException;
import com.codebotx.security.exception.exception.TokenRefreshException;
import com.codebotx.security.jwt.JwtUtils;
import com.codebotx.security.repository.RoleRepository;
import com.codebotx.security.repository.UserRepository;
import com.codebotx.security.service.RefreshTokenService;
import com.codebotx.security.service.UserDetailsImpl;
import com.codebotx.security.service.UserDetailsServiceImpl;
import com.fossm.authorization.context.DefaultUserContextHolder;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// @CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UserDetailsServiceImpl userservice;

    // @Autowired
    // DefaultUserContextHolder defaultUserContextHolder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    RefreshTokenService refreshTokenService;

    @GetMapping("/get")
    @PreAuthorize("hasRole('USER')")
    public String helloWorld(){
        return "Hello WOrld";
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        
        System.out.println("SignUp route 01");
        
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }


        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail()
                // encoder.encode(signUpRequest.getPassword())
                );

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                if (role.equals("admin")) {
                    Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(adminRole);
                } else {
                    Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        try {
            System.out.println("Login route 1");

            UserDetailsImpl user = (UserDetailsImpl) userservice.loadUserByUsername(loginRequest.getUsername());

            // Validate the email matches the provided username
            if (!user.getEmail().equals(loginRequest.getEmail())) {
                throw new SignInException("Email does not match the username.");
            }

            // Create an authentication token manually without a password
            UsernamePasswordAuthenticationToken authentication =
            new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());


            // Authentication authentication = authenticationManager
            //         .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

                    System.out.println("Login route 2");
                    System.out.println("HI I AM Authentication obj       \n"+authentication);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            System.out.println("Login route 3");

            String jwt = jwtUtils.generateJwtToken(authentication);
            System.out.println("Login route 4");


            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());


            // defaultUserContextHolder.setId(userDetails.getId());
            // defaultUserContextHolder.setUsername(userDetails.getUsername());
        

            System.out.println("Login route 5");

            RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
            System.out.println("Login route 6");

            return ResponseEntity.ok(new JwtResponse(
                    jwt,
                    refreshToken.getToken(),
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    roles));
                    
        } catch (AuthenticationException exception) {
            System.out.println("Login route 7");

            throw new SignInException(exception.getMessage());
        }

    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@Valid @RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtils.generateTokenFromUsername(user.getUsername());
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }

}

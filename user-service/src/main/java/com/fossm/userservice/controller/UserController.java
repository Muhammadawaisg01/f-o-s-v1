package com.fossm.userservice.controller;

// import com.codebotx.security.controllers.AuthController;
// import com.codebotx.security.data.payload.request.LoginRequest;
// import com.codebotx.security.data.payload.request.SignupRequest;

import com.fossm.userservice.dto.request.LoginRequest;
import com.fossm.userservice.dto.request.SignupRequest;


import com.fossm.swagger.controller.CommonController;
// import com.fossm.userservice.authclient.AuthClient;
import com.fossm.userservice.dto.BooleanResult;
import com.fossm.userservice.dto.UserAvatarDto;
import com.fossm.userservice.dto.UserProfileDto;
import com.fossm.userservice.dto.request.EmailRequestDto;
import com.fossm.userservice.dto.request.StatusUpdateRequest;
import com.fossm.userservice.dto.request.UserAvatarRequest;
import com.fossm.userservice.dto.request.UserCreateRequest;
import com.fossm.userservice.dto.request.UserReportRequest;
import com.fossm.userservice.dto.response.ConnectionResponse;
import com.fossm.userservice.service.UserReportService;
import com.fossm.userservice.service.facade.UserFacade;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
// import org.springframework.cloud.openfeign.FeignContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")

public class UserController implements CommonController {

    // @Autowired
    // private final AuthClient authClient;

    private final UserFacade userFacade;
    private final UserReportService userReportService;


    // @PostMapping("/signin")
    // public ResponseEntity<?> signIn(@RequestBody LoginRequest request) {
    //     System.out.println("I am in User Controller      "+request.toString());
    //     return authClient.signIn(request);
    // }

    // @PostMapping("/signup")
    // public ResponseEntity<?> signUp(@RequestBody SignupRequest newUser) {
    //     return authClient.signUp(newUser);
    // }


    @GetMapping("/content_creator")
    @PreAuthorize("hasAuthority('CONTENT_CREATOR')")
    public String helloWorld(){
        return "HELLO, I am the Content Creator";
    }

    @GetMapping("/viewer")
    @PreAuthorize("hasAuthority('VIEWER')")
    public String helloWorld1(){
        return "HELLO, I am the VIEWER";
    }


  @Operation(summary = "Get all users (paged)", description = "Returns a page of users")
  @GetMapping
  // @PreAuthorize("hasAuthority('CONTENT_CREATOR')")
  public Page<UserAvatarDto> getUsers(@PageableDefault(size = 20) Pageable pageable) {
    return userFacade.getAllUsers(pageable);
  }

  @PostMapping(path = "/avatar/list")
  public List<UserAvatarDto> getAvatars(@RequestBody UserAvatarRequest request) {
    return userFacade.getAvatars(request);
  }

  @Operation(summary = "Create a user", description = "Creates new user in the application")
  @PostMapping
  public UserAvatarDto createUser(@RequestBody @Valid UserCreateRequest request) {
    return userFacade.createUser(request);
  }

  @GetMapping("/search")
  @PreAuthorize("hasAuthority('CONTENT_CREATOR')")
  List<UserAvatarDto> searchByUsername(@RequestParam String username) {
    return userFacade.searchUsersByUsername(username);
  }

  @Operation(summary = "Get current user", description = "Returns current user data (ID parsed from JWT)")
  @GetMapping("/current")
  public UserProfileDto getCurrentUser() {
    return userFacade.getCurrentUser();
  }

  @Operation(summary = "Get specific user by ID", description = "Returns user data")
  @GetMapping("/{userId}")
  UserProfileDto getUser(@PathVariable UUID userId) {
    System.out.println("HI I AM IN getUser MEthod");
    return userFacade.getUserById(userId);
  }

  @Operation(summary = "Get all users following me (paged)", description = "Returns a page of users")
  @GetMapping("/current/connections")
  public List<UserAvatarDto> getConnections(@PageableDefault(size = 20) Pageable pageable) {
    return userFacade.getConnections(pageable);
  }

  @Operation(summary = "Get all users connected by me (paged)", description = "Returns a page of users")
  @GetMapping("/current/connected")
  public List<UserAvatarDto> getConnected(@PageableDefault(size = 20) Pageable pageable) {
    return userFacade.getConnected(pageable);
  }

  @Operation(summary = "Update user status", description = "Updates user status or creates it")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PostMapping("/current/status")
  void updateUserStatus(@RequestBody StatusUpdateRequest request) {
    userFacade.updateUserStatus(request);
  }

  @PostMapping(path = "/connect")
  public ConnectionResponse connectUser(@RequestParam UUID connection) {
    return userFacade.connectUser(connection);
  }

  @DeleteMapping(path = "/disconnect")
  public ConnectionResponse disconnectUser(@RequestParam UUID connection) {
    return userFacade.disconnectUser(connection);
  }

  @PostMapping(path = "/connection/accept")
  public ConnectionResponse acceptConnection(@RequestParam UUID connection) {
    return userFacade.acceptConnection(connection);
  }

  @DeleteMapping(path = "/connection/reject")
  public ConnectionResponse rejectConnection(@RequestParam UUID connection) {
    return userFacade.rejectConnection(connection);
  }

  @Operation(summary = "Report a user", description = "Sends report about a user")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PostMapping("/report")
  void reportUser(@RequestBody @Valid UserReportRequest userReportDto) {
    userReportService.reportUser(userReportDto);
  }

  @Operation(summary = "Check email", description = "Check if email is valid and unique")
  @ResponseStatus(HttpStatus.OK)
  @PostMapping("/check-email")
  @PreAuthorize("hasAuthority('VIEWER') or hasAuthority('CONTENT_CREATOR')")
  BooleanResult checkUniqueEmail(@RequestBody @Valid EmailRequestDto emailDto) {
    return userFacade.checkUniqueEmail(emailDto.email());
  }

}

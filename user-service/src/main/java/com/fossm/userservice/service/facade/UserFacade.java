package com.fossm.userservice.service.facade;

import com.fossm.authorization.context.UserContextHolder;
import com.fossm.userservice.dto.BooleanResult;
import com.fossm.userservice.dto.UserAvatarDto;
import com.fossm.userservice.dto.UserProfileDto;
import com.fossm.userservice.dto.request.StatusUpdateRequest;
import com.fossm.userservice.dto.request.UserAvatarRequest;
import com.fossm.userservice.dto.request.UserCreateRequest;
import com.fossm.userservice.dto.response.ConnectionResponse;
import com.fossm.userservice.exception.BadRequestException;
import com.fossm.userservice.mapper.UserMapper;
import com.fossm.userservice.model.Connection;
import com.fossm.userservice.service.ConnectionService;
import com.fossm.userservice.service.RoleService;
import com.fossm.userservice.service.UserService;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.stream.Collectors.toSet;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserFacade {

  private final UserContextHolder userContextHolder;
  private final UserService userService;
  private final RoleService roleService;
  private final ConnectionService connectionService;
  private final UserMapper userMapper;

  public Page<UserAvatarDto> getAllUsers(Pageable pageable) {
    var usersPage = userService.getUsers(pageable);
    return usersPage.map(userMapper::toUserAvatarDto);
  }

  public List<UserAvatarDto> getAvatars(UserAvatarRequest request) {
    var avatars = userService.getUsers(request.userIds()).stream()
        .map(userMapper::toUserAvatarDto).collect(Collectors.toList());
    var userId = this.getUserId();
    var connections = connectionService.getConnected(userId, request.userIds());
    for (UserAvatarDto avatarDto : avatars) {
      connections.stream().filter(connection -> avatarDto.getId().equals(connection.getConnectedId()))
          .findFirst().ifPresent(connection -> avatarDto.setConnected(true).setPending(connection.isPending()));
    }
    return avatars;
  }

  public List<UserAvatarDto> searchUsersByUsername(String username) {
    return userService.searchUsersByUsername(username).stream()
        .map(userMapper::toUserAvatarDto).collect(Collectors.toList());
  }

  public UserProfileDto getCurrentUser() {
    var userId = this.getUserId();
    var user = userService.getUser(userId);
    return userMapper.toUserProfileDto(user);
  }

  public UserProfileDto getUserById(UUID userId) {
    log.info("User id fetched from frontend is:"+userId);
    var user = userService.getUser(userId);
    var actorId = this.getUserId();
    var profileDto = userMapper.toUserProfileDto(user);
    connectionService.getConnection(actorId, userId)
        .ifPresent(connection -> profileDto.setConnected(true).setPending(connection.isPending()));
    return profileDto;
  }

  //Create new user
  public UserAvatarDto createUser(UserCreateRequest request) {
    var user = userMapper.toEntity(request);
    var roles = request.roles()
        .stream()
        .map(roleDto -> roleService.getByName(roleDto.name()))
        .collect(toSet());
    user.setRoles(roles);
    userService.save(user);
    return userMapper.toUserAvatarDto(user);
  }

  @Transactional
  public void updateUserStatus(StatusUpdateRequest request) {
    var userId = this.getUserId();
    var user = userService.getUser(userId);
    userMapper.mergeStatus(request, user);
  }

  public ConnectionResponse connectUser(UUID connectedId) {
    var userId = this.getUserId();
    var connectingUser = userService.getUser(connectedId);
    boolean pending = connectingUser.isPrivate();
    var connection = Connection.builder()
        .userId(userId)
        .connectedId(connectedId)
        .pending(pending)
        .build();
    connectionService.save(connection);
    //TODO: if private notify user about connection request
    return new ConnectionResponse(true, pending);
  }

  @Transactional
  public ConnectionResponse disconnectUser(UUID connectedId) {
    var userId = this.getUserId();
    connectionService.delete(userId, connectedId);
    return new ConnectionResponse(false, null);
  }

  public List<UserAvatarDto> getConnections(Pageable pageable) {
    var userId = this.getUserId();
    var connections = connectionService.getConnections(userId, pageable);
    var ids = connections.stream().map(Connection::getUserId).toList();
    var users = userService.getUsers(ids);
    var list = users.stream().map(userMapper::toUserAvatarDto).collect(Collectors.toList());
    var connected = connectionService.getConnected(userId, ids);
    for (UserAvatarDto avatarDto : list) {
      avatarDto.setConnected(false);
      connected.stream().filter(connection -> avatarDto.getId().equals(connection.getUserId()))
          .findFirst().ifPresent(connection -> avatarDto.setConnected(true).setPending(connection.isPending()));
    }
    list.sort(Comparator.comparing(UserAvatarDto::getUsername));
    return list;
  }

  public List<UserAvatarDto> getConnected(Pageable pageable) {
    var userId = this.getUserId();
    var connections = connectionService.getConnected(userId, pageable);
    var ids = connections.stream().map(Connection::getConnectedId).toList();
    var users = userService.getUsers(ids);
    var list = users.stream().map(userMapper::toUserAvatarDto).collect(Collectors.toList());
    list.forEach(userAvatarDto -> userAvatarDto.setConnected(true));
    list.sort(Comparator.comparing(UserAvatarDto::getUsername));
    return list;
  }

  @Transactional
  public ConnectionResponse acceptConnection(UUID connectingId) {
    var connectedId = this.getUserId();
    var optional = connectionService.getConnection(connectingId, connectedId);
    var connection = optional.orElseThrow(() -> {
      String msg = String.format("Connection between connecting user '%s' and connected user '%s' doesn't exist",
          connectingId, connectedId);
      log.error(msg);
      return new BadRequestException(msg);
    });
    connection.setPending(false);
    return new ConnectionResponse(true, null);
  }

  @Transactional
  public ConnectionResponse rejectConnection(UUID connectingId) {
    var connectedId = this.getUserId();
    connectionService.getConnection(connectingId, connectedId)
        .ifPresent(connection -> connectionService.delete(connectingId, connectedId));
    return new ConnectionResponse(false, null);
  }

  public BooleanResult checkUniqueEmail(String email) {
    boolean exists = userService.checkEmailExists(email);
    return new BooleanResult(!exists);
  }

  private UUID getUserId() {
    return userContextHolder.getId()
        .orElseThrow(() -> {
          var msg = "Unable to retrieve user ID from context.";
          log.error(msg);
          return new BadRequestException(HttpStatus.FORBIDDEN, HttpStatus.FORBIDDEN.name(), msg);
        });
  }

}

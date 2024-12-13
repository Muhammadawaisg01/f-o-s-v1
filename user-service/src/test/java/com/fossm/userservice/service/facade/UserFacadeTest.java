package com.fossm.userservice.service.facade;

import com.fossm.authorization.context.UserContextHolder;
import com.fossm.userservice.dto.UserProfileDto;
import com.fossm.userservice.mapper.SettingMapper;
import com.fossm.userservice.mapper.UserMapper;
import com.fossm.userservice.model.Connection;
import com.fossm.userservice.model.User;
import com.fossm.userservice.service.ConnectionService;
import com.fossm.userservice.service.RoleService;
import com.fossm.userservice.service.UserService;

import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import static com.fossm.userservice.util.PredefinedEntities.USER;
import static com.fossm.userservice.util.PredefinedEntities.USER_DTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {UserFacade.class})
@ExtendWith(MockitoExtension.class)
class UserFacadeTest {

  @Mock
  private UserContextHolder userContextHolder;
  @Mock
  private UserMapper userMapper;
  @Mock
  private SettingMapper settingMapper;
  @Mock
  private UserService userService;
  @Mock
  private RoleService roleService;
  @Mock
  private ConnectionService connectionService;

  @InjectMocks
  private UserFacade userFacade;

//  @Test
//  void testGetUsers() {
//    // Setup
//    Pageable pageable = Pageable.ofSize(1).withPage(0);
//    Page<User> usersPage = new PageImpl<>(List.of(USER));
//    when(userService.getUsers(any(Pageable.class))).thenReturn(usersPage);
//    when(userMapper.toUserProfileDto(any(User.class))).thenReturn(USER_DTO);
//
//    // Run the test
//    Page<UserDto> actualResult = userServiceFacade.getAllUsers(pageable);
//
//    // Verify the results
//    assertNotNull(actualResult);
//    assertEquals(1, actualResult.getTotalElements());
//  }

  @Test
  void testGetUserDtoById() {
    // Setup
    UUID userId = USER.getId();
    var connection = Connection.builder().userId(userId).connectedId(userId).build();

    when(userContextHolder.getId()).thenReturn(Optional.of(userId));
    when(userService.getUser(any(UUID.class))).thenReturn(USER);
    when(userMapper.toUserProfileDto(any(User.class))).thenReturn(USER_DTO);
    when(connectionService.getConnection(any(UUID.class), any())).thenReturn(Optional.ofNullable(connection));

    // Test the method
    UserProfileDto actualUserDto = userFacade.getUserById(userId);

    // Verify the results
    assertNotNull(actualUserDto);
    assertEquals(USER_DTO, actualUserDto);
  }

  @Test
  void testGetCurrentUserDto() {
    // Setup
    UUID userId = UUID.randomUUID();
    when(userContextHolder.getId()).thenReturn(Optional.of(userId));
    when(userService.getUser(any(UUID.class))).thenReturn(USER);
    when(userMapper.toUserProfileDto(any(User.class))).thenReturn(USER_DTO);

    // Test the method
    UserProfileDto actualCurrentUserProfileDto = userFacade.getCurrentUser();

    // Verify the results
    assertNotNull(actualCurrentUserProfileDto);
    assertEquals(USER_DTO, actualCurrentUserProfileDto);
  }

//  @Test
//  void testCreateUserFromDto() {
//    // Setup
//    when(userService.saveUser(any(User.class))).thenReturn(USER);
//    when(userMapper.toEntity(any(UserProfileDto.class))).thenReturn(USER);
//    when(settingMapper.toEntity(any(SettingDto.class))).thenReturn(SETTING);
//    when(userMapper.toUserProfileDto(any(User.class))).thenReturn(USER_DTO);
//
//    // Test the method
//    UserDto actualUserDto = userServiceFacade.createUser(USER_DTO);
//
//    // Verify the results
//    assertNotNull(actualUserDto);
//    assertNotNull(actualUserDto.id());
//    assertSame(USER_DTO, actualUserDto);
//  }

}
package com.fossm.userservice.service;

import com.fossm.exception.common.NotFoundException;
import com.fossm.userservice.model.User;
import com.fossm.userservice.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static com.fossm.userservice.util.PredefinedEntities.USER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserService userService;

  @Test
  void testGetUsers() {
    // Setup
    Pageable pageable = Pageable.ofSize(10);
    Page<User> expectedPage = mock(Page.class);
    when(userRepository.findAll(any(Pageable.class))).thenReturn(expectedPage);

    // Run the test
    Page<User> resultPage = userService.getUsers(pageable);

    // Verify the results
    assertEquals(expectedPage, resultPage);
  }

  @Test
  void testGetUser() {
//    todo
//    // Setup
//    when(userRepository.findById(any(UUID.class))).thenReturn(Optional.of(USER));
//
//    // Run the test
//    User resultUser = userService.getUser(USER.getId());
//
//    // Verify the results
//    assertEquals(USER, resultUser);
  }

  @Test
  void testGetUser_WithUserNotFound() {
    // Setup
    when(userRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

    // Run the test
    // Verify the results
    assertThrows(NotFoundException.class, () -> userService.getUser(USER.getId()));
  }

  @Test
  void testGetUserById() {
//    // Setup
//    when(userRepository.findById(any(UUID.class))).thenReturn(Optional.of(USER));
//
//    // Run the test
//    User resultUser = userService.getUser(UUID.randomUUID());
//
//    // Verify the results
//    assertEquals(USER, resultUser);
  }

  @Test
  void testCreateUser() {
    // Setup
    when(userRepository.save(any(User.class))).thenReturn(USER);

    // Run the test
    User result = userService.save(USER);

    // Verify the results
    assertEquals(USER, result);
  }
}

package com.fossm.userservice.service;

import com.fossm.exception.common.NotFoundException;
import com.fossm.userservice.model.User;
import com.fossm.userservice.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

  @Autowired
  private UserRepository repository;

  public Page<User> getUsers(Pageable pageable) {
    return repository.findAll(pageable);
  }

  public User getUser(UUID userId) {
    User user = repository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
    var now = LocalDateTime.now();
    var statusExpiration = user.getStatusExpirationDate();
    if (statusExpiration != null && statusExpiration.isBefore(now)) {
      this.clearStatus(user);
    }
    return user;
  }

  //Create  new user
  public User save(User user) {
    return repository.save(user);
  }

  public List<User> searchUsersByUsername(String username) {
    return repository.getAllByUsernameStartsWithIgnoreCase(username);
  }

  public boolean checkEmailExists(String email) {
    return repository.existsByEmail(email);
  }

  private void clearStatus(User user) {
    user.setStatusText(null);
    user.setStatusFileId(null);
    user.setStatusExpirationDate(null);
  }

  public List<User> getUsers(List<UUID> idList) {
    return repository.findAllById(idList);
  }

}

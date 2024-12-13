package com.fossm.userservice.repository;

import com.fossm.userservice.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userServiceUserRepository")
public interface UserRepository extends JpaRepository<User, UUID> {

  @EntityGraph(attributePaths = {"roles"})
  Page<User> findAll(Pageable pageable);

  @EntityGraph(attributePaths = {"roles", "settings"})
  Optional<User> findById(UUID userId);

  List<User> getAllByUsernameStartsWithIgnoreCase(String username);

  boolean existsByEmail(String email);

}

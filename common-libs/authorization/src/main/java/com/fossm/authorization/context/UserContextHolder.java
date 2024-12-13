package com.fossm.authorization.context;

import java.util.Optional;
import java.util.UUID;

public interface UserContextHolder {

  void setId(UUID id);

  Optional<UUID> getId();

  void setUsername(String username);

  Optional<String> getUsername();

  void remove();
}

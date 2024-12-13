package com.fossm.authorization.context;

import java.util.Optional;
import java.util.UUID;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import static org.springframework.web.context.WebApplicationContext.SCOPE_REQUEST;

@Component("userContextHolder")
@Scope(value = SCOPE_REQUEST, proxyMode = ScopedProxyMode.INTERFACES)
public class DefaultUserContextHolder implements UserContextHolder {

  private final ThreadLocal<UUID> idHolder = new ThreadLocal<>();
  private final ThreadLocal<String> usernameHolder = new ThreadLocal<>();

  public void setId(UUID userId) {
    idHolder.set(userId);
  }

  public Optional<UUID> getId() {
    return Optional.ofNullable(idHolder.get());
  }

  @Override
  public void setUsername(String username) {
    usernameHolder.set(username);
  }

  @Override
  public Optional<String> getUsername() {
    return Optional.ofNullable(usernameHolder.get());
  }

  public void remove() {
    idHolder.remove();
    usernameHolder.remove();
  }

}
package com.fossm.userservice.service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRelationService {

  public List<UUID> getSupporterIdsForUserId(UUID userId) {
    return Collections.emptyList();
  }

  public List<UUID> getConnectionIdsForUser(UUID userId) {
    return Collections.emptyList();
  }

  public boolean isUserSupportedByAnother(UUID userId, UUID anotherId) {
    return false;
  }

  public boolean isUserConnectedWithAnother(UUID userId, UUID anotherId) {
    return false;
  }

}

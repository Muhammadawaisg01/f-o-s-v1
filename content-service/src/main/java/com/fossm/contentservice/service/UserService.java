package com.fossm.contentservice.service;

import com.fossm.contentservice.client.UserClient;
import com.fossm.contentservice.dto.user.AvatarProfileDto;
import com.fossm.contentservice.dto.user.request.AvatarProfilesRequest;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.util.List.*;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserClient client;

  public List<AvatarProfileDto> getAvatarProfiles(List<UUID> userIds) {
    return client.getAvatarProfiles(new AvatarProfilesRequest(userIds));
  }

  public AvatarProfileDto getAvatarProfile(UUID userId) {
    var avatarProfiles = client.getAvatarProfiles(new AvatarProfilesRequest(of(userId)));
    return avatarProfiles.get(0);
  }

}

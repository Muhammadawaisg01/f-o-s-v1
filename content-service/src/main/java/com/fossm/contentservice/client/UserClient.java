package com.fossm.contentservice.client;

import com.fossm.contentservice.dto.user.AvatarProfileDto;
import com.fossm.contentservice.dto.user.request.AvatarProfilesRequest;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service", url = "${user.url}")
public interface UserClient {

  @PostMapping(path = "/users/avatar/list")
  List<AvatarProfileDto> getAvatarProfiles(@RequestBody AvatarProfilesRequest request);

}

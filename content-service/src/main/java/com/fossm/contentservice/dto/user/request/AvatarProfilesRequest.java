package com.fossm.contentservice.dto.user.request;

import java.util.List;
import java.util.UUID;
import lombok.Builder;

@Builder
public record AvatarProfilesRequest(
    List<UUID> userIds
) {

}
package com.fossm.userservice.dto.request;

import java.util.List;
import java.util.UUID;

public record UserAvatarRequest(
    List<UUID> userIds
) {

}

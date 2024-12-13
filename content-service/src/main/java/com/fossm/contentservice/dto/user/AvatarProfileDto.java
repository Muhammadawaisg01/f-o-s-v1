package com.fossm.contentservice.dto.user;

import java.util.UUID;

public record AvatarProfileDto(
    UUID id,
    String avatarUrl,
    String username,
    String firstName,
    String lastName,
    boolean verified,
    String topCreatorMark,
    Boolean connected
) {

}

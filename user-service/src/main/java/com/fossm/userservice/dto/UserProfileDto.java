package com.fossm.userservice.dto;

import com.fossm.userservice.model.enums.Language;
import com.fossm.userservice.model.enums.TopCreatorMark;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

/**
 * DTO for {@link com.fossm.userservice.model.User}
 */
@Builder
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserProfileDto implements Serializable {

  UUID id;
  LocalDateTime createdAt;
  String username;
  Language preferredLanguage;
  String firstName;
  String lastName;
  String profileAvatarUrl;
  String profileCoverUrl;
  String profileDescription;
  String statusText;
  String statusFileId;
  LocalDateTime statusExpirationDate;
  boolean verified;
  TopCreatorMark topCreatorMark;
  boolean connected;
  boolean pending;
  Set<RoleDto> roles;

}
package com.fossm.userservice.dto;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserAvatarDto {

  UUID id;

  String username;

  String avatarUrl;

  String firstName;

  String lastName;

  boolean verified;

  String topCreatorMark;

  Boolean connected;

  Boolean pending;

}
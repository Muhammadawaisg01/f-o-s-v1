package com.fossm.userservice.mapper;

import com.fossm.userservice.dto.UserAvatarDto;
import com.fossm.userservice.dto.UserProfileDto;
import com.fossm.userservice.dto.request.StatusUpdateRequest;
import com.fossm.userservice.dto.request.UserCreateRequest;
import com.fossm.userservice.model.User;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(uses = {RoleMapper.class, SettingMapper.class})
public interface UserMapper {

  @Mapping(target = "topCreatorMark", constant = "NONE")
  User toEntity(UserCreateRequest request);

  @Mapping(target = "avatarUrl", source = "profileAvatarUrl")
  UserAvatarDto toUserAvatarDto(User user);

  UserProfileDto toUserProfileDto(User user);

  void mergeStatus(StatusUpdateRequest request, @MappingTarget User user);

}
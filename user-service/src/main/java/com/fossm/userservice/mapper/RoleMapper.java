package com.fossm.userservice.mapper;

import com.fossm.userservice.dto.RoleDto;
import com.fossm.userservice.model.Role;

import org.mapstruct.Mapper;

@Mapper
public interface RoleMapper {

  RoleDto toDto(Role role);

}
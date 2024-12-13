package com.fossm.userservice.service;

import com.fossm.userservice.model.Role;
import com.fossm.userservice.model.enums.RoleName;
import com.fossm.userservice.repository.RoleRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

  private final RoleRepository roleRepository;

  public Role getByName(RoleName name) {
    return roleRepository.findRoleByName(name);
  }
}

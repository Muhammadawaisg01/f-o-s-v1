package com.fossm.userservice.service;

import com.fossm.userservice.model.Role;
import com.fossm.userservice.model.enums.RoleName;
import com.fossm.userservice.repository.RoleRepository;
import com.fossm.userservice.service.RoleService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.fossm.userservice.util.PredefinedEntities.VIEWER_ROLE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoleServiceTest {

  @Mock
  private RoleRepository roleRepository;

  @InjectMocks
  private RoleService roleService;

  @Test
  void testGetByName() {
    // Setup
    when(roleRepository.findRoleByName(any(RoleName.class))).thenReturn(VIEWER_ROLE);

    // Run the test
    Role actualRole = roleService.getByName(VIEWER_ROLE.getName());

    // Verify the results
    assertEquals(VIEWER_ROLE, actualRole);
  }
}
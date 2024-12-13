package com.fossm.userservice.repository;

import com.fossm.userservice.model.Role;
import com.fossm.userservice.model.enums.RoleName;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

  Role findRoleByName(RoleName name);

}

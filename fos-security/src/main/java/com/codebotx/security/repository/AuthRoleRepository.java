package com.codebotx.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codebotx.security.data.AuthERole;
import com.codebotx.security.data.AuthRole;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthRoleRepository extends JpaRepository<AuthRole, UUID> {

    Optional<AuthRole> findByName(AuthERole name);

}

package com.fossm.userservice.dto;

import com.fossm.userservice.model.Role;
import com.fossm.userservice.model.enums.RoleName;

import java.io.Serializable;
import lombok.Builder;

/**
 * DTO for {@link Role}
 */
@Builder
public record RoleDto(
    RoleName name
) implements Serializable {

}
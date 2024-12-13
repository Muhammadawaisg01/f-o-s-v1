package com.fossm.userservice.dto.request;

import com.fossm.userservice.dto.RoleDto;
import com.fossm.userservice.model.enums.Language;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

public record UserCreateRequest(
    @NotNull
    UUID id,
    @Email
    String email,
    @Pattern(regexp = "[\\p{L}\\p{M}\\p{S}\\p{N}\\p{P}]+")
    String username,
    @NotNull
    Language preferredLanguage,
    @NotEmpty
    Set<RoleDto> roles
) implements Serializable {

}

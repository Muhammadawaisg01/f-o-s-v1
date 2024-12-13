package com.fossm.userservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record EmailRequestDto(@NotNull @Email String email) {

}

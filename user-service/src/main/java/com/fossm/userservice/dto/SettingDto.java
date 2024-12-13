package com.fossm.userservice.dto;

import com.fossm.userservice.model.Setting;
import com.fossm.userservice.model.enums.UserSetting;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.UUID;
import lombok.Builder;

/**
 * DTO for {@link Setting}
 */
@Builder
public record SettingDto(
    @JsonIgnore UUID id,
    UserSetting userSetting,
    String value,
    boolean enabled
) implements Serializable {

}
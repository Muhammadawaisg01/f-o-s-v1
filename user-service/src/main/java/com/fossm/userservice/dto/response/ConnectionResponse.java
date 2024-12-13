package com.fossm.userservice.dto.response;

import lombok.Builder;

@Builder
public record ConnectionResponse(
    boolean connected,
    Boolean pending
) {

}

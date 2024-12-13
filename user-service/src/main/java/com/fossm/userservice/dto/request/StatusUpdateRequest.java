package com.fossm.userservice.dto.request;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record StatusUpdateRequest(
    String statusText,
    String statusFileId,
    LocalDateTime statusExpirationDate
) implements Serializable {

}

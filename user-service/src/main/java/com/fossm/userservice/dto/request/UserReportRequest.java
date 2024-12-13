package com.fossm.userservice.dto.request;

import com.fossm.userservice.model.enums.ReportReason;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import lombok.Builder;

/**
 * DTO for {@link com.fossm.userservice.model.UserReport}
 */
@Builder
public record UserReportRequest(
    @NotNull
    UUID reporterId,
    @NotNull
    UUID reportedId,
    UUID postId,
    @NotNull
    ReportReason reason,
    @NotNull @Size(max = 200)
    String explanation,
    List<String> screenshotUploadIds
) implements Serializable {

}
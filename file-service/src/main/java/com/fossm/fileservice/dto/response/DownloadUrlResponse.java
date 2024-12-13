package com.fossm.fileservice.dto.response;

import java.io.Serializable;
import lombok.Builder;

@Builder
public record DownloadUrlResponse(
    String uploadId,
    String downloadUrl
) implements Serializable {

}

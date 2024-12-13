package com.fossm.fileservice.dto.request;

import java.io.Serializable;

public record CreateMultipartUploadRequest(
    String filename,

    String fileType

) implements Serializable {

}

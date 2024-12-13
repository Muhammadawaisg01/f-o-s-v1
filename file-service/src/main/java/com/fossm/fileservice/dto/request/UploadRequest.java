package com.fossm.fileservice.dto.request;

import java.io.Serializable;

public record UploadRequest(
    String filename,

    String fileType

) implements Serializable {

}

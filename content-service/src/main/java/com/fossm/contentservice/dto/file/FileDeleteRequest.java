package com.fossm.contentservice.dto.file;

import java.util.List;

public record FileDeleteRequest(
    List<String> keys,
    boolean isPrivate
) {

}

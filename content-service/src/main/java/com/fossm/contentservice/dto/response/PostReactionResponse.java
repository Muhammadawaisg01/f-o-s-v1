package com.fossm.contentservice.dto.response;

import java.io.Serializable;
import lombok.Builder;

@Builder
public record PostReactionResponse(
    long reactionCount,
    boolean liked
) implements Serializable {

}

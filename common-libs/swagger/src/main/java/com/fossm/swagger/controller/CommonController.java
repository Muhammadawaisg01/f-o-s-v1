package com.fossm.swagger.controller;

import com.fossm.exception.dto.ExceptionResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@ApiResponse(
    responseCode = "400",
    description = "Request validation exception",
    content = {@Content(schema = @Schema(implementation = ExceptionResponse.class))}
)
@ApiResponse(
    responseCode = "404",
    description = "Not found",
    content = {@Content(schema = @Schema(implementation = ExceptionResponse.class))}
)
@ApiResponse(
    responseCode = "500",
    description = "Internal server error",
    content = {@Content(schema = @Schema(implementation = ExceptionResponse.class))}
)
public interface CommonController {

}

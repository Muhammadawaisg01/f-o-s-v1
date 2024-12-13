package com.fossm.contentservice.controller;

import com.fossm.contentservice.model.enums.ContentCategory;
import com.fossm.swagger.controller.CommonController;

import io.swagger.v3.oas.annotations.Operation;
import java.util.Set;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categories")
public class ContentCategoryController implements CommonController {

  @Operation(summary = "Get all content categories", description = "Returns a list of allowed content categories")
  @GetMapping
  public Set<ContentCategory> getContentCategoryValues() {
    return Set.of(ContentCategory.values());
  }

}
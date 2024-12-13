package com.fossm.contentservice.controller;

import com.fossm.contentservice.service.HashtagService;
import com.fossm.contentservice.validation.annotations.Hashtag;
import com.fossm.swagger.controller.CommonController;

import io.swagger.v3.oas.annotations.Operation;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hashtags")
@Validated
@RequiredArgsConstructor
public class HashtagController implements CommonController {

  private final HashtagService hashtagService;

  @Operation(summary = "Search similar hashtags", description = "Returns a list of similar hashtags")
  @GetMapping(path = "/search")
  public Set<String> searchHashtags(@RequestParam @Hashtag String tag) {
    return hashtagService.searchHashtags(tag);
  }

}

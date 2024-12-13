package com.fossm.contentservice.controller;

import com.fossm.contentservice.dto.CommentDto;
import com.fossm.contentservice.dto.PostDetailsDto;
import com.fossm.contentservice.dto.PostDto;
import com.fossm.contentservice.dto.PostPreviewDto;
import com.fossm.contentservice.dto.request.PostCreateRequest;
import com.fossm.contentservice.dto.request.PostUpdateRequest;
import com.fossm.contentservice.dto.response.PostReactionResponse;
import com.fossm.contentservice.dto.user.AvatarProfileDto;
import com.fossm.contentservice.facade.PostFacade;
import com.fossm.contentservice.validation.annotations.Hashtag;
import com.fossm.swagger.controller.CommonController;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
@Validated
public class PostController implements CommonController {

  private final PostFacade postFacade;

  @Operation(summary = "Get all owner posts (paged)", description = "Returns a list of posts")
  @GetMapping("/current")
  public Page<PostDto> getOwnerPosts(
      @PageableDefault(size = 20, sort = {"createdAt"}, direction = Direction.DESC) Pageable pageable) {
    return postFacade.getOwnerPosts(pageable);
  }

  @Operation(summary = "Get all owner post previews (paged)", description = "Returns a list of posts")
  @GetMapping("/current/previews")
  public Page<PostPreviewDto> getOwnerPostPreviews(
      @PageableDefault(size = 20, sort = {"createdAt"}, direction = Direction.DESC) Pageable pageable) {
    return postFacade.getOwnerPostPreviews(pageable);
  }

  @Operation(summary = "Get posts for main feed (paged)", description = "Returns a list of posts")
  @GetMapping
  public Page<PostDto> getMainFeed(
      @PageableDefault(size = 20, sort = {"createdAt"}, direction = Direction.DESC) Pageable pageable) {
    return postFacade.getMainFeed(pageable);
  }

  @Operation(summary = "Get purchased posts for viewer (paged)", description = "Returns a list of posts")
  @GetMapping("/purchased")
  public Page<PostDto> getViewerPurchasedPosts(
      @PageableDefault(size = 20, sort = {"created_at"}, direction = Direction.DESC) Pageable pageable) {
    return postFacade.getViewerPurchasedPosts(pageable);
  }

  @Operation(summary = "Get posts by hashtag (paged)", description = "Returns a list of posts")
  @GetMapping("/by-hashtag")
  public Page<PostDto> getPostsByHashtag(
      @RequestParam @Hashtag String hashtag,
      @PageableDefault(size = 20, sort = {"createdAt"}, direction = Direction.DESC) Pageable pageable) {
    return postFacade.getPostsByHashtag(hashtag, pageable);
  }

  @Operation(summary = "Get posts for viewer by profile ID (paged)", description = "Returns a list of posts")
  @GetMapping("/view")
  public Page<PostDto> getViewerPosts(
      @RequestParam UUID userId,
      @PageableDefault(size = 20, sort = {"createdAt"}, direction = Direction.DESC) Pageable pageable) {
    return postFacade.getViewerPosts(userId, pageable);
  }

  @Operation(summary = "Get post previews for viewer by profile ID (paged)", description = "Returns a list of posts")
  @GetMapping("/preview")
  public Page<PostPreviewDto> getViewerPreviewPosts(
      @RequestParam UUID userId,
      @PageableDefault(size = 20, sort = {"createdAt"}, direction = Direction.DESC) Pageable pageable) {
    return postFacade.getViewerPreviewPosts(userId, pageable);
  }

  @Deprecated
  @GetMapping("/current/{postId}")
  PostDetailsDto getOwnPostDetails(@PathVariable UUID postId) {
    return null;
  }

  @Deprecated
  @GetMapping("/{postId}/view")
  PostDetailsDto getViewPostDetails(@PathVariable UUID postId) {
    return null;
  }

  @PostMapping
  PostDetailsDto createPost(@RequestBody @Valid PostCreateRequest request) {
    return postFacade.createPost(request);
  }

  @Operation(summary = "Update existing post", description = "Updates existing post")
  @PatchMapping("/{postId}")
  PostDetailsDto updatePost(@PathVariable UUID postId, @RequestBody @Valid PostUpdateRequest request) {
    return postFacade.updatePost(postId, request);
  }

  @Operation(summary = "Delete a post", description = "Delete a post")
  @DeleteMapping("/{postId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deletePost(@PathVariable UUID postId) {
    postFacade.deletePost(postId);
  }

  @PostMapping("/{postId}/reaction")
  PostReactionResponse addPostReaction(@PathVariable UUID postId) {
    return postFacade.addPostReaction(postId);
  }

  @DeleteMapping("/{postId}/reaction")
  PostReactionResponse removePostReaction(@PathVariable UUID postId) {
    return postFacade.removePostReaction(postId);
  }

  @GetMapping("/{postId}/reaction")
  Page<AvatarProfileDto> getPostReactionList(
      @PathVariable UUID postId,
      @PageableDefault(size = 20, sort = {"createdAt"}, direction = Direction.DESC) Pageable pageable) {
    return postFacade.getPostReactionList(postId, pageable);
  }

  @GetMapping("/{postId}/reaction/search")
  List<AvatarProfileDto> searchPostReactions(@PathVariable UUID postId, @RequestParam String username) {
    return postFacade.searchPostReactions(postId, username);
  }

  @PostMapping("/{postId}/comments")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  void addComment(@PathVariable UUID postId) {
  }

  @GetMapping("/{postId}/comments")
  List<CommentDto> getComments(@PathVariable UUID postId) {
    return null;
  }

  @PostMapping("/{postId}/comments/{commentId}/reaction")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  void addCommentReaction(@PathVariable UUID postId, @PathVariable UUID commentId) {
  }

  @PostMapping("/{postId}/buy")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  void buyPost(@PathVariable UUID postId) {
    postFacade.buyPost(postId);
  }

}
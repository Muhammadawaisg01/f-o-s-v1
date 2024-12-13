package com.fossm.contentservice.facade;

import com.fossm.authorization.context.UserContextHolder;
import com.fossm.contentservice.dto.PostDetailsDto;
import com.fossm.contentservice.dto.PostDto;
import com.fossm.contentservice.dto.PostPreviewDto;
import com.fossm.contentservice.dto.request.PostCreateRequest;
import com.fossm.contentservice.dto.request.PostUpdateRequest;
import com.fossm.contentservice.dto.response.PostReactionResponse;
import com.fossm.contentservice.dto.user.AvatarProfileDto;
import com.fossm.contentservice.exception.BadRequestException;
import com.fossm.contentservice.mapper.PostMapper;
import com.fossm.contentservice.model.Content;
import com.fossm.contentservice.model.Post;
import com.fossm.contentservice.model.PostReaction;
import com.fossm.contentservice.model.PurchasedPost;
import com.fossm.contentservice.service.CommentService;
import com.fossm.contentservice.service.ContentService;
import com.fossm.contentservice.service.FileService;
import com.fossm.contentservice.service.PostReactionService;
import com.fossm.contentservice.service.PostService;
import com.fossm.contentservice.service.PurchasedPostService;
import com.fossm.contentservice.service.UserService;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostFacade {

  private final PostService postService;
  private final PurchasedPostService purchasedPostService;
  private final PostReactionService postReactionService;
  private final UserService userService;
  private final ContentService contentService;
  private final FileService fileService;
  private final CommentService commentService;
  private final PostMapper postMapper;
  private final UserContextHolder userContextHolder;

  public Page<PostDto> getOwnerPosts(Pageable pageable) {
    var userId = getUserId();
    var ownerPosts = postService.getOwnerPosts(userId, pageable);
    var avatarProfile = userService.getAvatarProfile(userId);

    return ownerPosts.map(post -> toOwnerPostDto(post, avatarProfile, userId));
  }

  private PostDto toOwnerPostDto(Post post, AvatarProfileDto avatarProfile, UUID ownerId) {
    var postId = post.getId();
    var hidePriceDisplay = false;
    var commentCount = commentService.getCommentsCount(postId);
    var shareCount = 0L;
    var reactionCount = postReactionService.getReactionCount(postId);
    var liked = postReactionService.isLiked(postId, ownerId);

    return postMapper.toDto(post, avatarProfile, hidePriceDisplay, commentCount, shareCount, reactionCount, liked);
  }

  public Page<PostPreviewDto> getOwnerPostPreviews(Pageable pageable) {
    var userId = getUserId();
    var ownerPosts = postService.getOwnerPosts(userId, pageable);

    return ownerPosts.map(post -> postMapper.toPreviewDto(post, false));
  }

  public Page<PostDto> getMainFeed(Pageable pageable) {
    var userId = getUserId();
    var mainFeedPosts = postService.getMainFeedPosts(userId, pageable);
    var purchasedPostIds = purchasedPostService.getPurchasedPostIds(userId);

    return mainFeedPosts.map(post -> this.toViewerPostDto(post, userId, purchasedPostIds));
  }

  private PostDto toViewerPostDto(Post post, UUID userId, List<UUID> purchasedPostIds) {
    var postId = post.getId();
    var postUserId = post.getUserId();
    var avatarProfile = userService.getAvatarProfile(postUserId);
    var hidePriceDisplay = purchasedPostIds.contains(postId);
    var commentCount = commentService.getCommentsCount(postId);
    var shareCount = 0L;
    var reactionCount = postReactionService.getReactionCount(postId);
    var liked = postReactionService.isLiked(postId, userId);

    return postMapper.toDto(post, avatarProfile, hidePriceDisplay, commentCount, shareCount, reactionCount, liked);
  }

  @Transactional(readOnly = true)
  public Page<PostDto> getViewerPurchasedPosts(Pageable pageable) {
    var userId = getUserId();
    var purchasedPosts = postService.getPurchasedPosts(userId, pageable);

    return purchasedPosts.map(post -> this.toPurchasedPostDto(post, userId));
  }

  private PostDto toPurchasedPostDto(Post post, UUID userId) {
    var postId = post.getId();
    var postUserId = post.getUserId();
    var avatarProfile = userService.getAvatarProfile(postUserId);
    var hidePriceDisplay = true;
    var commentCount = commentService.getCommentsCount(postId);
    var shareCount = 0L;
    var reactionCount = postReactionService.getReactionCount(postId);
    var liked = postReactionService.isLiked(postId, userId);

    return postMapper.toDto(post, avatarProfile, hidePriceDisplay, commentCount, shareCount, reactionCount, liked);
  }

  public Page<PostDto> getPostsByHashtag(String hashtag, Pageable pageable) {
    var posts = postService.getPostsByHashtag(hashtag, pageable);

    return postMapper.toDto(posts);
  }

  public Page<PostDto> getViewerPosts(UUID viewedUserId, Pageable pageable) {
    /*add check that searched userId is not current userId*/
    var userId = getUserId();
    var posts = postService.getViewerPosts(viewedUserId, pageable);
    var avatarProfile = userService.getAvatarProfile(viewedUserId);
    var purchasedPostIds = purchasedPostService.getPurchasedPostIds(userId);

    return posts.map(post -> this.getPostByUserId(post, avatarProfile, userId, purchasedPostIds));

  }

  private PostDto getPostByUserId(Post post, AvatarProfileDto avatarProfile, UUID userId, List<UUID> purchasedPostIds) {
    var postId = post.getId();
    var hidePriceDisplay = purchasedPostIds.contains(postId);
    var commentCount = commentService.getCommentsCount(postId);
    var shareCount = 0L;
    var reactionCount = postReactionService.getReactionCount(postId);
    var liked = postReactionService.isLiked(postId, userId);

    return postMapper.toDto(post, avatarProfile, hidePriceDisplay, commentCount, shareCount, reactionCount, liked);
  }

  public Page<PostPreviewDto> getViewerPreviewPosts(UUID viewedUserId, Pageable pageable) {
    /*add check that searched userId is not current userId*/
    var userId = getUserId();
    var posts = postService.getViewerPosts(viewedUserId, pageable);
    var purchasedPostIds = purchasedPostService.getPurchasedPostIds(userId);

    return posts.map(post -> postMapper.toPreviewDto(post, purchasedPostIds.contains(post.getId())));
  }

  public PostDetailsDto createPost(PostCreateRequest request) {
    var userId = getUserId();
    var username = getUsername();
    var post = postMapper.toEntity(request, userId, username);
    var contents = request.contents().stream()
        .map(dto -> createContent(post, dto.fileName(), userId))
        .toList();
    post.setContents(contents);

    return postMapper.toDetailsDto(postService.save(post));
  }

  private Content createContent(Post post, String fileName, UUID userId) {
    int dot = fileName.lastIndexOf(".");
    var fileNameWithoutFormat = dot == -1 ? "" : fileName.substring(0, dot);
    var fileFormat = fileName.substring(dot);

    var content = new Content();
    content.setMediaKey("/" + userId + "/" + fileName);
    content.setThumbnailKey("/" + userId + "/" + fileNameWithoutFormat + "_thumbnail" + fileFormat);
    content.setThumbnailBlurKey("/" + userId + "/" + fileNameWithoutFormat + "_thumbnail_blur" + fileFormat);
    content.setPost(post);

    return content;
  }

  @Transactional
  public PostDetailsDto updatePost(UUID postId, PostUpdateRequest request) {
    var userId = getUserId();
    var post = postService.getOwnPost(postId, userId);
    postMapper.update(request, post);
    return postMapper.toDetailsDto(post);
  }

  @Transactional
  public void deletePost(UUID postId) {
    var userId = getUserId();
    var post = postService.getDetailedPost(postId, userId);
    if (purchasedPostService.isPurchased(postId)) {
      post.setDeleted(true)
          .setDescription(null)
          .setAllowComments(false)
          .setDoubleMonetization(false)
          .setPrice(null)
          .setViewCount(0L)
          .setCategories(null)
          .setHashtags(null);
      commentService.deleteAll(post.getComments());
      postReactionService.deleteAll(post.getReactions());
    } else {
      List<Content> contents = List.copyOf(post.getContents());
      contentService.deleteContents(postId);
      postService.deletePost(postId);
      CompletableFuture<Void> deleteMediaFuture = CompletableFuture.runAsync(() ->
          fileService.deleteMedia(contents));
      CompletableFuture<Void> deleteThumbnailsFuture = CompletableFuture.runAsync(() ->
          fileService.deleteThumbnails(contents));
      CompletableFuture.allOf(deleteMediaFuture, deleteThumbnailsFuture).join();
    }
  }

  public Page<AvatarProfileDto> getPostReactionList(UUID postId, Pageable pageable) {
    var userIds = postReactionService.getAllReactedUsersByPostId(postId, pageable);
    var avatarProfiles = userService.getAvatarProfiles(userIds);
    var count = postReactionService.getReactionCount(postId);
    return new PageImpl<>(avatarProfiles, pageable, count);
  }

  public PostReactionResponse addPostReaction(UUID postId) {
    var userId = getUserId();
    var username = getUsername();
    var post = postService.getPost(postId);
    PostReaction postReaction = PostReaction.builder()
        .userId(userId)
        .username(username)
        .post(post)
        .build();
    postReactionService.save(postReaction);
    var count = postReactionService.getReactionCount(postId);
    return PostReactionResponse.builder()
        .reactionCount(count)
        .liked(true)
        .build();
  }

  @Transactional
  public PostReactionResponse removePostReaction(UUID postId) {
    var userId = getUserId();
    postReactionService.delete(postId, userId);
    var count = postReactionService.getReactionCount(postId);
    return PostReactionResponse.builder()
        .reactionCount(count)
        .liked(false)
        .build();
  }

  public List<AvatarProfileDto> searchPostReactions(UUID postId, String username) {
    var userIds = postReactionService.searchPostReactions(postId, username);
    return userService.getAvatarProfiles(userIds);
  }

  public void buyPost(UUID postId) {
    var userId = getUserId();
    var post = postService.getPost(postId);
    purchasedPostService.save(PurchasedPost.builder()
        .userId(userId)
        .post(post)
        .build());
  }

  private UUID getUserId() {
    return userContextHolder.getId()
        .orElseThrow(() -> {
          var msg = "Unable to retrieve user ID from context.";
          log.error(msg);
          return new BadRequestException(msg);
        });
  }

  private String getUsername() {
    return userContextHolder.getUsername()
        .orElseThrow(() -> {
          var msg = "Unable to retrieve 'username' from context.";
          log.error(msg);
          return new BadRequestException(msg);
        });
  }

}

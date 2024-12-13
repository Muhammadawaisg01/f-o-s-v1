package com.fossm.contentservice.service;

import com.fossm.contentservice.exception.BadRequestException;
import com.fossm.contentservice.model.Post;
import com.fossm.contentservice.repository.PostRepository;
import com.fossm.exception.common.NotFoundException;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

  private final PostRepository postRepository;

  public Page<Post> getOwnerPosts(UUID userId, Pageable pageable) {
    return postRepository.findAllByUserId(userId, pageable);
  }

  public Page<Post> getMainFeedPosts(UUID userId, Pageable pageable) {
    return postRepository.findAllByUserIdNot(userId, pageable);
  }

  public Page<Post> getPurchasedPosts(UUID userId, Pageable pageable) {
    return postRepository.findPurchasedPosts(userId, pageable);
  }

  public Page<Post> getPostsByHashtag(String hashtag, Pageable pageable) {
    return postRepository.findPostsByHashtag(hashtag, pageable);
  }

  public Page<Post> getViewerPosts(UUID userId, Pageable pageable) {
    return postRepository.findAllByUserId(userId, pageable);
  }

  public Post getPost(UUID postId) {
    return postRepository.findById(postId).orElseThrow(() -> {
      var msg = String.format("Post '%s' not found", postId);
      log.error(msg);
      return new NotFoundException(msg);
    });
  }

  public Post getOwnPost(UUID postId, UUID userId) {
    return postRepository.findByIdAndUserId(postId, userId).orElseThrow(() -> {
      var msg = String.format("Post '%s' does not belong to a user '%s'.", postId, userId);
      log.error(msg);
      return new BadRequestException(msg);
    });
  }

  public Post getDetailedPost(UUID postId, UUID userId) {
    return postRepository.getByIdAndUserId(postId, userId).orElseThrow(() -> {
      var msg = String.format("Post '%s' does not belong to a user '%s'.", postId, userId);
      log.error(msg);
      return new BadRequestException(msg);
    });
  }

  public Post save(Post post) {
    return postRepository.save(post);
  }

  public void deletePost(UUID id) {
    postRepository.deleteById(id);
  }

}
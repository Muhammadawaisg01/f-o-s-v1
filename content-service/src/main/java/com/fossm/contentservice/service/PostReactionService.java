package com.fossm.contentservice.service;

import com.fossm.contentservice.exception.BadRequestException;
import com.fossm.contentservice.model.PostReaction;
import com.fossm.contentservice.model.projection.PostReactionUserIdProjection;
import com.fossm.contentservice.repository.PostReactionRepository;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostReactionService {

  private final PostReactionRepository repository;

  public PostReaction save(PostReaction postReaction) {
    return repository.saveAndFlush(postReaction);
  }

  public void delete(UUID postId, UUID userId) {
    if (!repository.existsByPostIdAndUserId(postId, userId)) {
      var msg = String.format("Post LIKE '%s' does not belong to a user '%s'.", postId, userId);
      log.error(msg);
      throw new BadRequestException(msg);
    }
    repository.deleteByPostIdAndUserId(postId, userId);
  }

  public void deleteAll(Set<PostReaction> postReactions) {
    repository.deleteAll(postReactions);
  }

  public long getReactionCount(UUID postId) {
    return repository.countByPostId(postId);
  }

  public List<UUID> getAllReactedUsersByPostId(UUID postId, Pageable pageable) {
    return repository.getAllUserIdByPostId(postId, pageable)
        .stream().map(PostReactionUserIdProjection::getUserId).collect(Collectors.toList());
  }

  public List<UUID> searchPostReactions(UUID postId, String username) {
    return repository.getAllUserIdByPostIdAndUsernameStartsWithIgnoreCase(postId, username)
        .stream().map(PostReactionUserIdProjection::getUserId).collect(Collectors.toList());
  }

  public boolean isLiked(UUID postId, UUID userId) {
    return repository.existsByPostIdAndUserId(postId, userId);
  }
}

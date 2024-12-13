package com.fossm.contentservice.repository;

import com.fossm.contentservice.model.PostReaction;
import com.fossm.contentservice.model.projection.PostReactionUserIdProjection;

import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostReactionRepository extends JpaRepository<PostReaction, UUID> {

  long countByPostId(UUID postId);

  boolean existsByPostIdAndUserId(UUID postId, UUID userId);

  void deleteByPostIdAndUserId(UUID postId, UUID userId);

  List<PostReactionUserIdProjection> getAllUserIdByPostId(UUID postId, Pageable pageable);

  List<PostReactionUserIdProjection> getAllUserIdByPostIdAndUsernameStartsWithIgnoreCase(UUID postId, String username);
}

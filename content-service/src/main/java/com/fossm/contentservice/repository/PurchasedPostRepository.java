package com.fossm.contentservice.repository;

import com.fossm.contentservice.model.PurchasedPost;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PurchasedPostRepository extends JpaRepository<PurchasedPost, UUID> {

  boolean existsByPostId(UUID postId);

  @Query("SELECT p.post.id FROM PurchasedPost p WHERE p.userId = :userId")
  List<UUID> findAllPurchasedPostIds(UUID userId);

}

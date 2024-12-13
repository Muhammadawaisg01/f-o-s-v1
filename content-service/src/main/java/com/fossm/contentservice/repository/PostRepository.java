package com.fossm.contentservice.repository;

import com.fossm.contentservice.model.Post;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {

  @EntityGraph(attributePaths = {"categories", "hashtags", "contents"})
  Page<Post> findAllByUserId(UUID userId, Pageable pageable);

  Optional<Post> findByIdAndUserId(UUID postId, UUID userId);

  @EntityGraph(attributePaths = {"categories", "hashtags", "comments", "reactions", "contents"})
  Optional<Post> getByIdAndUserId(UUID id, UUID userId);

  @EntityGraph(attributePaths = {"categories", "hashtags", "contents"})
  Page<Post> findAllByUserIdNot(UUID userId, Pageable pageable);

  @Query(nativeQuery = true,
      value = """ 
          select p.* from posts p join purchased_post pp on pp.post_id=p.id 
          where pp.user_id=?1 and p.user_id!=?1
          """)
  Page<Post> findPurchasedPosts(UUID userId, Pageable pageable);

  @Query(value = """
      SELECT p from Post p
      JOIN FETCH p.hashtags ht
      JOIN FETCH p.contents
      JOIN FETCH p.categories
      WHERE LOWER(ht) = LOWER(:hashtag)
      """)
  Page<Post> findPostsByHashtag(String hashtag, Pageable pageable);

  @Query(
      nativeQuery = true,
      value = """
          SELECT DISTINCT ht.hashtag FROM posts_hashtags ht
          WHERE LOWER(ht.hashtag) LIKE LOWER(CONCAT(:tag, '%'))
          """
  )
  Set<String> searchHashtags(String tag);

}
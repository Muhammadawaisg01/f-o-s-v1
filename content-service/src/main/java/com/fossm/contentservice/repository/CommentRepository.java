package com.fossm.contentservice.repository;

import com.fossm.contentservice.model.Comment;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {

  long countByPostId(UUID postId);

}

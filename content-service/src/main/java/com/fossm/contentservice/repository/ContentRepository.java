package com.fossm.contentservice.repository;

import com.fossm.contentservice.model.Content;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<Content, UUID> {

  void deleteAllByPostId(UUID postId);
}

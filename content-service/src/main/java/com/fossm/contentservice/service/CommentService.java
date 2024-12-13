package com.fossm.contentservice.service;

import com.fossm.contentservice.repository.CommentRepository;

import java.util.Collection;
import java.util.UUID;
import com.fossm.contentservice.model.Comment;
import com.fossm.contentservice.repository.CommentRepository;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

  private final CommentRepository repository;

  public long getCommentsCount(UUID postId) {
    return repository.countByPostId(postId);
  }

  public void deleteAll(Collection<Comment> comments) {
    repository.deleteAll(comments);
  }
}

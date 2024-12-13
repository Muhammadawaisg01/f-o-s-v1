package com.fossm.contentservice.service;

import com.fossm.contentservice.repository.ContentRepository;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContentService {

  private final ContentRepository contentRepository;

  public void deleteContents(UUID postId) {
    contentRepository.deleteAllByPostId(postId);
  }
}


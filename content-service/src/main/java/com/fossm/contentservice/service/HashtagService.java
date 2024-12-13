package com.fossm.contentservice.service;

import com.fossm.contentservice.repository.PostRepository;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HashtagService {

  private final PostRepository postRepository;

  public Set<String> searchHashtags(String tag) {
    return postRepository.searchHashtags(tag);
  }

}
package com.fossm.contentservice.service;

import com.fossm.contentservice.model.PurchasedPost;
import com.fossm.contentservice.repository.PurchasedPostRepository;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PurchasedPostService {

  private final PurchasedPostRepository repository;

  public boolean isPurchased(UUID postId) {
    return repository.existsByPostId(postId);
  }

  public List<UUID> getPurchasedPostIds(UUID userId) {
    return repository.findAllPurchasedPostIds(userId);
  }

  public void save(PurchasedPost purchasedPost) {
    repository.save(purchasedPost);
  }
}

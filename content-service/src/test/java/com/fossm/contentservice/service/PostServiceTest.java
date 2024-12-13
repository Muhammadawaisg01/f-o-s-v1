package com.fossm.contentservice.service;

import com.fossm.contentservice.model.Post;
import com.fossm.contentservice.repository.PostRepository;

import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import static com.fossm.contentservice.util.PredefinedEntities.DEFAULT_UUID;
import static com.fossm.contentservice.util.PredefinedEntities.POST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

  @Mock
  private PostRepository postRepository;
  @InjectMocks
  private PostService postService;

  @Test
  void getPosts() {
    // Setup
    Pageable pageable = Pageable.ofSize(10);
    Page<Post> expectedPosts = new PageImpl<>(List.of(POST));
    when(postRepository.findAllByUserId(eq(DEFAULT_UUID), any(Pageable.class))).thenReturn(expectedPosts);

    // Run the test
    Page<Post> actualPosts = postService.getOwnerPosts(POST.getUserId(), pageable);

    // Verify the results
    assertEquals(expectedPosts, actualPosts);
  }

  @Test
  void deletePost() {
    // Setup
    UUID postId = UUID.randomUUID();
    doNothing().when(postRepository).deleteById(any(UUID.class));

    // Run the test
    postService.deletePost(postId);

    // Verify the results
    verify(postRepository).deleteById(postId);
  }
}
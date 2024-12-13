package com.fossm.contentservice.facade;

import com.fossm.authorization.context.UserContextHolder;
import com.fossm.contentservice.model.Post;
import com.fossm.contentservice.service.CommentService;
import com.fossm.contentservice.service.PostReactionService;
import com.fossm.contentservice.service.PostService;
import com.fossm.contentservice.service.PurchasedPostService;

import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.fossm.contentservice.util.PredefinedEntities.DEFAULT_UUID;
import static com.fossm.contentservice.util.PredefinedEntities.POST;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostFacadeTest {

  @Mock
  private PostService postService;

  @Mock
  private PurchasedPostService purchasedPostService;

  @Mock
  private UserContextHolder userContextHolder;

  @Mock
  private CommentService commentService;

  @Mock
  private PostReactionService postReactionService;

  @InjectMocks
  private PostFacade postFacade;

  @Test
  void testDeletePost() {
    UUID userId = DEFAULT_UUID;
    UUID postId = DEFAULT_UUID;
    Post post = POST;

    when(userContextHolder.getId()).thenReturn(Optional.of(userId));
    when(postService.getDetailedPost(postId, userId)).thenReturn(post);
    when(purchasedPostService.isPurchased(postId)).thenReturn(true);
    doNothing().when(commentService).deleteAll(null);
    doNothing().when(postReactionService).deleteAll(anySet());

    postFacade.deletePost(postId);

    verify(postService).getDetailedPost(postId, userId);
    verify(purchasedPostService).isPurchased(postId);
    verify(commentService).deleteAll(post.getComments());
    verify(postReactionService).deleteAll(post.getReactions());
    verify(postService, never()).deletePost(any(UUID.class));

    Assertions.assertTrue(post.isDeleted());
    Assertions.assertNull(post.getDescription());
    Assertions.assertNotNull(post.getRelationType());
    Assertions.assertFalse(post.getAllowComments());
    Assertions.assertFalse(post.isDoubleMonetization());
    Assertions.assertEquals(0L, post.getViewCount());
    Assertions.assertNull(post.getPrice());
    Assertions.assertNull(post.getCategories());
    Assertions.assertNull(post.getHashtags());
  }
}

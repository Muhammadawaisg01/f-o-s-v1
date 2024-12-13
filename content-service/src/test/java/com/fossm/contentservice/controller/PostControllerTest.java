package com.fossm.contentservice.controller;

import com.fossm.contentservice.dto.PostDto;
import com.fossm.contentservice.repository.PostRepository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;
import java.util.UUID;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockHttpServletResponse;

import static com.fossm.contentservice.util.PredefinedEntities.ACCESS_TOKEN;
import static com.fossm.contentservice.util.PredefinedEntities.POST;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PostControllerTest extends AbstractMvcTest {

  private static UUID postId;

  @Autowired
  private PostRepository postRepository;

  @BeforeEach
  void setup() {
    postId = postRepository.save(POST).getId();
  }

//  @Test
//  @SneakyThrows
//  void getPosts() {
//    // Run the test
//    MockHttpServletResponse response = mockMvc.perform(
//            get("/api/v1/posts")
//                .header(HttpHeaders.AUTHORIZATION, "Bearer " + ACCESS_TOKEN)
//        )
//        .andExpect(status().isOk())
//        .andReturn()
//        .getResponse();
//
//    JsonNode rootNode = mapper.readTree(response.getContentAsString());
//    JsonNode contentNode = rootNode.get("content");
//    List<PostDto> postDtos = mapper.convertValue(contentNode, new TypeReference<>() {
//    });
//
//    // Verify the results
//    for (PostDto postDto : postDtos) {
//      assertTrue(postRepository.existsById(postDto.id()));
//    }
//  }

  @Test
  @SneakyThrows
  @Disabled("Not updated yet")
  void deletePost() {
    assertTrue(postRepository.existsById(postId));

    // Run the test
    mockMvc.perform(
            delete("/api/v1/posts/{postId}", postId)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + ACCESS_TOKEN)
        )
        .andExpect(status().is(204))
        .andReturn()
        .getResponse();

    // Verify the results
    assertFalse(postRepository.existsById(postId));
  }
}
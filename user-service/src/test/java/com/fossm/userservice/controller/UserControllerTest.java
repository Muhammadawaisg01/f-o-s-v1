package com.fossm.userservice.controller;

import com.fossm.userservice.dto.UserProfileDto;
import com.fossm.userservice.model.UserReport;
import com.fossm.userservice.repository.UserReportRepository;
import com.fossm.userservice.repository.UserRepository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.List;
import lombok.SneakyThrows;
import org.glassfish.jaxb.core.v2.TODO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static com.fossm.userservice.util.PredefinedEntities.STATUS_UPDATE_REQUEST;
import static com.fossm.userservice.util.PredefinedEntities.USER_DTO;
import static com.fossm.userservice.util.PredefinedEntities.USER_REPORT_DTO_SPAM;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
class UserControllerTest extends AbstractMvcTest {

  @Value("${test.jwt.token}")
  private String jwtToken;

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private UserReportRepository userReportRepository;
  //TODO
//  @Test
//  @SneakyThrows
//  void testGetUsers() {
//    // Run the test
//    MockHttpServletResponse response = mockMvc.perform(
//            get("/api/v1/users")
//        )
//        .andExpect(status().isOk())
//        .andReturn()
//        .getResponse();
//
//    JsonNode rootNode = mapper.readTree(response.getContentAsString());
//    JsonNode contentNode = rootNode.get("content");
//    List<UserProfileDto> userDtos = mapper.convertValue(contentNode, new TypeReference<>() {
//    });
//
//    // Verify the results
//    for (UserProfileDto userDto : userDtos) {
//      assertTrue(userRepository.existsById(userDto.id()));
//   }
//  }

//  @Test
//  @SneakyThrows
//  void testGetUser() {
//    // Run the test
//    MockHttpServletResponse response = mockMvc.perform(
//            get("/api/v1/users/{userId}", "1a9ac01b-1fe5-4dcc-a5be-0338b11184e9")
//        )
//        .andExpect(status().isOk())
//        .andReturn()
//        .getResponse();
//
//    UserProfileDto userDto = mapper.readValue(response.getContentAsString(), UserProfileDto.class);
//
//    // Verify the results
//    assertTrue(userRepository.existsById(userDto.id()));
//  }

//  @Test
//  @SneakyThrows
//  void testGetCurrentUser() {
//    // Run the test
//    MockHttpServletResponse response = mockMvc.perform(
//            get("/api/v1/users/current")
//                .header("Authorization", "Bearer " + jwtToken)
//        )
//        .andExpect(status().isOk())
//        .andReturn()
//        .getResponse();
//
//    UserProfileDto userDto = mapper.readValue(response.getContentAsString(), UserProfileDto.class);
//
//    // Verify the results
//    assertTrue(userRepository.existsById(userDto.id()));
//  }

//  @Test
//  @SneakyThrows
//  void testCreateUser() {
//    // Setup
//    String request = mapper.registerModule(new JavaTimeModule())
//        .writeValueAsString(USER_DTO);
//
//    // Run the test
//    MockHttpServletResponse response = mockMvc.perform(
//            post("/api/v1/users")
//                .contentType(APPLICATION_JSON)
//                .content(request)
//                .accept(APPLICATION_JSON)
//        )
//        .andExpect(status().isOk())
//        .andReturn()
//        .getResponse();
//
//    String actual = response.getContentAsString();
//
//    // Verify the results
//    assertNotNull(response.getContentAsString());
//    assertFalse(actual.isBlank());
//  }
//
//  @Test
//  @SneakyThrows
//  void testUpdateUserStatus() {
//    // Setup
//    String request = mapper.writeValueAsString(STATUS_UPDATE_REQUEST);
//
//    // Run the test
//    MockHttpServletResponse response = mockMvc.perform(
//            post("/api/v1/users/current/status")
//                .header("Authorization", "Bearer " + jwtToken)
//                .contentType(APPLICATION_JSON)
//                .content(request)
//                .accept(APPLICATION_JSON)
//        )
//        .andExpect(status().isOk())
//        .andReturn()
//        .getResponse();
//
//    UserProfileDto actualUserDto = mapper.readValue(response.getContentAsString(), UserProfileDto.class);
//
//    // Verify the results
//    assertEquals(STATUS_UPDATE_REQUEST.statusText(), actualUserDto.statusText());
//    assertEquals(STATUS_UPDATE_REQUEST.statusFileId(), actualUserDto.statusFileId());
//    assertEquals(STATUS_UPDATE_REQUEST.statusExpirationDate(),
//        actualUserDto.statusExpirationDate());
//  }

//  @Test
//  @SneakyThrows
//  void testClearUserStatus() {
//    // Run the test
//    mockMvc.perform(
//            delete("/api/v1/users/current/status")
//                .header("Authorization", "Bearer " + jwtToken)
//        )
//        .andExpect(status().is(204))
//        .andReturn()
//        .getResponse();
//  }

//  @Test
//  @SneakyThrows
//  void testReportUser() {
//    // Run the test
//    mockMvc.perform(
//            post("/api/v1/users/report")
//                .contentType(APPLICATION_JSON)
//                .content(mapper.writeValueAsBytes(USER_REPORT_DTO_SPAM))
//                .accept(APPLICATION_JSON)
//        )
//        .andExpect(status().isOk())
//        .andReturn()
//        .getResponse();
//
//    UserReport userReport = userReportRepository.findByReporterIdAndReportedId(
//        USER_REPORT_DTO_SPAM.reporterId(), USER_REPORT_DTO_SPAM.reportedId()
//    );
//
//    // Verify the results
//    assertNotNull(userReport);
// }
}

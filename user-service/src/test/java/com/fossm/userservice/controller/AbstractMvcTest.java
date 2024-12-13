package com.fossm.userservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public abstract class AbstractMvcTest {

  @Autowired
  protected MockMvc mockMvc;

  protected final ObjectMapper mapper = new ObjectMapper()
      .registerModule(new JavaTimeModule());

//  @Test
//  void contextLoads() {
//    boolean isLoaded = true;
//    assertTrue(isLoaded);
//  }

}

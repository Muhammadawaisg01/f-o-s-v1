package com.fossm.contentservice.controller;

import com.fossm.authorization.context.UserContextHolder;
import com.fossm.contentservice.request_filter.AuthFilter;
import com.fossm.contentservice.model.enums.ContentCategory;
import com.fossm.swagger.controller.CommonController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.Set;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categories")
@SecurityRequirement(name = "Bearer Authentication")
public class ContentCategoryController implements CommonController {

  UserContextHolder holder = AuthFilter.userContextHolder;
  
  @Operation(summary = "Get all content categories", description = "Returns a list of allowed content categories")
  @GetMapping
  public Set<ContentCategory> getContentCategoryValues() {
    
    System.out.println("I am the ContextHolder    \n");

    System.out.println("I am the ID   "+holder.getId());
    System.out.println("I am the Username   "+holder.getUsername());
     
    return Set.of(ContentCategory.values());
  }

    @GetMapping("/content_creator")
    @PreAuthorize("hasAuthority('CONTENT_CREATOR')")
    public String helloWorld(){
        return "HELLO, I am the Content Creator";
    }

    @GetMapping("/viewer")
    @PreAuthorize("hasAuthority('VIEWER')")
    public String helloWorld1(){
        return "HELLO, I am the VIEWER";
    }


}
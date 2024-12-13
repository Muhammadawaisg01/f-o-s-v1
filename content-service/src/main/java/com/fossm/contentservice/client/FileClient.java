package com.fossm.contentservice.client;

import com.fossm.contentservice.dto.file.FileDeleteRequest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "file-service", url = "${file.url}")
public interface FileClient {

  @DeleteMapping(path = "/files")
  void deleteMedia(@RequestBody FileDeleteRequest deleteRequest);

}

package com.fossm.contentservice.service;

import com.fossm.contentservice.client.FileClient;
import com.fossm.contentservice.dto.file.FileDeleteRequest;
import com.fossm.contentservice.model.Content;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileService {

  private final FileClient fileClient;

  public void deleteMedia(List<Content> contents) {
    List<String> keys = contents.stream().map(Content::getMediaKey).collect(Collectors.toList());
    FileDeleteRequest mediaDeleteRequest = new FileDeleteRequest(keys, true);
    log.info("Requesting to delete the following files: {}", keys);
//    fileClient.deleteMedia(mediaDeleteRequest); //TODO: uncomment when file-service will be ready
  }

  public void deleteThumbnails(List<Content> contents) {
    List<String> keys = contents.stream()
        .flatMap(content -> Stream.of(content.getThumbnailKey(), content.getThumbnailBlurKey()))
        .collect(Collectors.toList());
    FileDeleteRequest thumbnailDeleteRequest = new FileDeleteRequest(keys, false);
    log.info("Requesting to delete the following files: {}", keys);
//    fileClient.deleteMedia(thumbnailDeleteRequest);
  }
}

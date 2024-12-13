package com.fossm.userservice.consumers;

import com.fossm.kafka.events.AudioStatusUploadedEvent;
import com.fossm.userservice.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserFileConsumer {

  private final UserService userService;

  @KafkaListener(
      topics = "${topics.inbound.audio-status-uploaded}",
      groupId = "${topics.consumer-groups.audio-status}"
  )
  public void updateAudioStatus(AudioStatusUploadedEvent event) {
    log.info("Received kafka message {}", event);
  }

}

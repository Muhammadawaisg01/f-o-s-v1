package com.fossm.fileservice.producer;

import com.fossm.kafka.events.AudioStatusUploadedEvent;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserFileProducer {

  @Value(value = "${topics.outbound.audio-status-uploaded}")
  private String audioStatusUploadedTopic;

  private final KafkaTemplate<UUID, Object> kafkaTemplate;

  public void sendAudioStatusUploadedEvent(AudioStatusUploadedEvent event) {
    kafkaTemplate.send(audioStatusUploadedTopic, event.getUserId(), event);
  }

}

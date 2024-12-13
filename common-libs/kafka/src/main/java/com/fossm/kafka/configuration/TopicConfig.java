package com.fossm.kafka.configuration;

import com.fossm.kafka.properties.TopicProperties;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.List;

@Configuration
@AllArgsConstructor
public class TopicConfig {

  private final TopicProperties topicProperties;
  private final KafkaAdmin kafkaAdmin;

  @Bean
  public void createKafkaTopics() {
    NewTopic[] topics = getTopics();
    if (topics.length > 0) {
      kafkaAdmin.createOrModifyTopics(topics);
    } else {
      System.out.println("No Kafka topics configured in TopicProperties.");
    }
  }

  private NewTopic[] getTopics() {
    List<String> topicsList = topicProperties.getTopics();
    if (topicsList == null || topicsList.isEmpty()) {
      return new NewTopic[0];  // Return an empty array if topics are null or empty
    }

    return topicsList.stream()
            .map(topic -> TopicBuilder.name(topic).build())
            .toArray(NewTopic[]::new);
  }
}

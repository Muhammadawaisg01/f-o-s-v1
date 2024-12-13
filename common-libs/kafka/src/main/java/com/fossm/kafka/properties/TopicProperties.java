package com.fossm.kafka.properties;

import java.util.List;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "kafka")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TopicProperties {

  List<String> topics;
  String dltSuffix;

}
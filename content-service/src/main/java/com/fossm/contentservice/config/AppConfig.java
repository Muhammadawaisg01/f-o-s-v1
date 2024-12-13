package com.fossm.contentservice.config;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
public class AppConfig {

  @Bean
  public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
    var timeModule = new JavaTimeModule();
    timeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder()
        .modules(timeModule)
        .serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
        .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
            SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS);
    return new MappingJackson2HttpMessageConverter(builder.build());
  }

}

package com.fossm.fileservice.configuration.properties;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@ConfigurationProperties(prefix = "cloudflare.r2")
public class R2Properties {

  String region;

  String secretAccessKey;

  String accessKeyId;

  String url;

  String verificationBucketName;

  String userFileBucketName;

  Long chunkSize;

  Integer uploadSignatureDurationSeconds;

  Integer downloadSignatureDurationSeconds;

  Integer cacheControlMaxAge;

  TransferManagerProperties transferManager;

  @Data
  @FieldDefaults(level = AccessLevel.PRIVATE)
  public static class TransferManagerProperties {

    Integer threadNumber;

    Long minimumPartSizeInMb;

    Double targetThroughputInGbps;
  }

}
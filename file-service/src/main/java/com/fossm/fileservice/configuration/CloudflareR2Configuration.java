package com.fossm.fileservice.configuration;

import com.fossm.fileservice.configuration.properties.R2Properties;

import java.net.URI;
import java.net.URISyntaxException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProviderChain;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

@Configuration
@RequiredArgsConstructor
@Profile({"dev", "stage", "prod", "local"})
public class CloudflareR2Configuration {

  private final R2Properties r2Properties;

  @Bean
  public S3Client r2Client() throws URISyntaxException {
    return S3Client.builder()
        .region(Region.of(r2Properties.getRegion()))
        .endpointOverride(new URI(r2Properties.getUrl()))
        .credentialsProvider(getCredentials())
        .build();
  }

  @Bean
  public S3Presigner r2Presigner() throws URISyntaxException {
    return S3Presigner.builder()
        .region(Region.of(r2Properties.getRegion()))
        .endpointOverride(new URI(r2Properties.getUrl()))
        .credentialsProvider(getCredentials())
        .build();
  }


  private AwsCredentialsProvider getCredentials() {
    return AwsCredentialsProviderChain.of(
        StaticCredentialsProvider.create(
            AwsBasicCredentials.create(
                r2Properties.getAccessKeyId(), r2Properties.getSecretAccessKey()
            )
        )
    );
  }

}

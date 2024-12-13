package com.fossm.database.datasource;

import com.fossm.exception.common.ServerException;
import com.fossm.exception.util.ExceptionCode;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.providers.DefaultAwsRegionProviderChain;
import software.amazon.awssdk.services.rds.RdsClient;
import software.amazon.awssdk.services.rds.RdsUtilities;
import software.amazon.awssdk.services.rds.model.GenerateAuthenticationTokenRequest;
import software.amazon.awssdk.services.rds.model.RdsException;

@Slf4j
public class AuroraIAMDataSource extends HikariDataSource {

  @Override
  public String getPassword() {
    return getToken();
  }

  private String getToken() {
    var region = new DefaultAwsRegionProviderChain().getRegion();
    var hostnamePort = getHostnamePort();

    RdsClient rdsClient = RdsClient.builder()
        .region(region)
        .credentialsProvider(DefaultCredentialsProvider.create())
        .build();
    try (rdsClient) {
      RdsUtilities utilities = rdsClient.utilities();
      GenerateAuthenticationTokenRequest tokenRequest = GenerateAuthenticationTokenRequest.builder()
          .credentialsProvider(DefaultCredentialsProvider.create())
          .hostname(hostnamePort.getFirst())
          .port(hostnamePort.getSecond())
          .username(getUsername())
          .build();

      return utilities.generateAuthenticationToken(tokenRequest);

    } catch (RdsException e) {
      var msg = "Connection to RDS failed: " + e;
      log.error(msg);
      throw new ServerException(HttpStatus.INTERNAL_SERVER_ERROR, ExceptionCode.SERVER_ERROR, msg);
    }
  }

  /* JDBC URL, read from spring.datasource, has a standard URL format, like: jdbc:postgresql://localhost:5432/test_database */
  private Pair<String, Integer> getHostnamePort() {
    var slashing = getJdbcUrl().indexOf("//") + 2;
    var sub = getJdbcUrl().substring(slashing, getJdbcUrl().indexOf("/", slashing));
    var splitted = sub.split(":");
    return Pair.of(splitted[0], Integer.parseInt(splitted[1]));
  }

}
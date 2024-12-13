package com.fossm.logging.config.rest;

import ch.qos.logback.access.servlet.TeeFilter;
import ch.qos.logback.access.tomcat.LogbackValve;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!prod")
@ConditionalOnProperty(value = "logging.level.com.fossm", havingValue = "DEBUG")
public class AccessLogConfiguration {

  @Bean
  public TomcatServletWebServerFactory tomcatServletWebServerFactory() {
    TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
    LogbackValve logbackValve = new LogbackValve();
    logbackValve.setFilename(String.format("conf/%s", LogbackValve.DEFAULT_FILENAME));
    factory.addContextValves(logbackValve);
    return factory;
  }

  @Bean
  public FilterRegistrationBean requestResponseFilter() {

    final FilterRegistrationBean filterRegBean = new FilterRegistrationBean();
    TeeFilter filter = new TeeFilter();
    filterRegBean.setFilter(filter);
    filterRegBean.addUrlPatterns("*");
    filterRegBean.setName("Request Response Filter");
    filterRegBean.setAsyncSupported(Boolean.TRUE);
    return filterRegBean;
  }

}

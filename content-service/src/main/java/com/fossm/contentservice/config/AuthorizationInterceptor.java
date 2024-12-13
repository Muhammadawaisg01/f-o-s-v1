package com.fossm.contentservice.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class AuthorizationInterceptor implements RequestInterceptor {

  @Override
  public void apply(RequestTemplate template) {
    var requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    if (requestAttributes == null) {
      return;
    }
    var request = requestAttributes.getRequest();
    var bearer = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (bearer == null) {
      return;
    }
    template.header(HttpHeaders.AUTHORIZATION, bearer);
  }

}

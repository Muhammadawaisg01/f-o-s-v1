package com.fossm.logging.config.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

  /**
   * Pointcut method for all service layer methods.
   */
  @Pointcut("@within(org.springframework.stereotype.Service)")
  protected void allServiceMethods() {
    // This point cut is for all service layer methods
  }

  /**
   * Pointcut method for all repository layer methods.
   */
  @Pointcut("@within(org.springframework.stereotype.Repository)")
  protected void allRepositoryMethods() {
    // This point cut is for all repository layer methods
  }

  @Around("allServiceMethods() || allRepositoryMethods()")
  public Object aroundServicesAndRepositories(final ProceedingJoinPoint proceedingJoinPoint)
      throws Throwable {
    final Object result;
    final String methodName = proceedingJoinPoint.getSignature().getName();
    final String className = proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName();
    final Object[] args = proceedingJoinPoint.getArgs();
    log.debug("Called method '{}.{}' with arguments: {}", className, methodName, args);
    try {
      result = proceedingJoinPoint.proceed();
      log.debug("Method '{}.{}' returned value: {}", className, methodName, result);
    } catch (final Throwable e) {
      log.error("Error occurred during method '{}.{}' call: {}", className, methodName,
          e.getMessage());
      throw e;
    }
    return result;
  }
}

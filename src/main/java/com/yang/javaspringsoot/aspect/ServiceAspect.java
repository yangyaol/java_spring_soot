package com.yang.javaspringsoot.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author:
 * @create: 2020-08-17 19:21
 **/
@Aspect
@Component
public class ServiceAspect  {
    private final static Logger LOGGER = LoggerFactory.getLogger(ControllerAspect.class);
    //找的是接口的方法路径
    @Pointcut(value = "@annotation(com.yang.javaspringsoot.aspect.ServiceAnnotation)")
    @Order(2)
    public void servicePointCut(){}

    @Before(value = "com.yang.javaspringsoot.aspect.ServiceAspect.servicePointCut()")
    public void beforeService(JoinPoint joinPoint){
        LOGGER.debug("=====this is beforService====");
    }

    @Around(value = "com.yang.javaspringsoot.aspect.ServiceAspect.servicePointCut()")
    public Object aroundService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        LOGGER.debug("=====this is arountService====");
        return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
    }

    @After(value = "com.yang.javaspringsoot.aspect.ServiceAspect.servicePointCut()")
    public void afterService(){
        LOGGER.debug("=====this is afterService====");
    }

}

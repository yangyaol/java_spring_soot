package com.yang.javaspringsoot.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author:
 * @create: 2020-08-17 18:48
 **/
@Aspect
@Component
public class ControllerAspect {

    private final static Logger LOGGER = LoggerFactory.getLogger(ControllerAspect.class);

    /**
     * 关联在方法上的切点
     * 第一个*代表返回类型不限
     * 第二个*代表module下所有子包
     * 第三个*代表所有类
     * 第四个*代表所有方法
     * (..) 代表参数不限
     * Order 代表优先级，数字越小优先级越高
     */
    @Pointcut("execution(public * com.yang.javaspringsoot.modules.*.controller.*.*(..))")
    @Order(1)
    public void controllerPointCut(){

    }

    @Before(value = "com.yang.javaspringsoot.aspect.ControllerAspect.controllerPointCut()")
    public void beforeController(JoinPoint joinPoint){
        LOGGER.debug("==== This is before controller ====");
        ServletRequestAttributes attributes=
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request =attributes.getRequest();
        LOGGER.debug("请求来源: " + request.getRemoteAddr());
        LOGGER.debug("请求URL: " + request.getRequestURI().toString());
        LOGGER.debug("请求方式: " + request.getMethod());
        LOGGER.debug("相应方法: " +
                 joinPoint.getSignature().getDeclaringTypeName() + "." +
                joinPoint.getSignature().getName());
        LOGGER.debug("请求参数: " + Arrays.toString(joinPoint.getArgs()));
    }

    @Around(value = "com.yang.javaspringsoot.aspect.ControllerAspect.controllerPointCut()")
    public Object aroundController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        LOGGER.debug("==== This is around controller ==== ");
        return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
    }

    @After(value = "com.yang.javaspringsoot.aspect.ControllerAspect.controllerPointCut()")
    public void afterController(){
        LOGGER.debug("==== This is after controller ====");
    }
}

package com.yang.javaspringsoot.modules.common.controller;

import com.yang.javaspringsoot.modules.common.vo.Result;
import org.apache.shiro.authz.AuthorizationException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author:
 * @create: 2020-08-26 19:33
 **/
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionHandlerController {

    @ResponseBody
    @ExceptionHandler(value = AuthorizationException.class)
    public Result<String> handle403(){
        return new Result<>(Result.ResultStatus.FAILD.status,
                "", "/common/403");
    }
}

package org.xunyin.officeautomationdemo.exception;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public SaResult exceptionHandler(Exception e){
        e.printStackTrace();
        return SaResult.error(e.getMessage());
    }
}

package com.xslgy.core.exception;

import com.xslgy.core.utils.Result;
import com.xslgy.core.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
public class XSLGYExceptionHandler
{

    @ExceptionHandler(XSLGYException.class)
    public Result<Void> handlerIOTException(XSLGYException e)
    {
        return ResultUtils.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public Result<Void> handlerNoHandlerFoundException(Exception e)
    {
        log.error(e.getMessage(), e);
        return ResultUtils.error(404, "路径不存在");
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public Result<Void> handlerDuplicateKeyException(DuplicateKeyException e)
    {
        log.error(e.getMessage(), e);
        return ResultUtils.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "数据库中已经存在该记录");
    }

    @ExceptionHandler(Exception.class)
    public Result<Void> handlerException(Exception e)
    {
        log.error(e.getMessage(), e);
        return ResultUtils.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }
}

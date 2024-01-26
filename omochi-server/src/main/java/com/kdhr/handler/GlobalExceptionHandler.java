package com.kdhr.handler;

import com.kdhr.constant.MessageConstant;
import com.kdhr.exception.BaseException;
import com.kdhr.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全域異常處理器，處理專案中拋出的業務異常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕獲業務異常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex) {
        log.error("異常訊息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

    @ExceptionHandler
    public Result exceptionHandler(SQLIntegrityConstraintViolationException ex) {
        String message = ex.getMessage();
        if (message.contains("Duplicate entry")) {
            String[] split = message.split(" ");
            String username = split[2];
            String msg = username + MessageConstant.ALREADY_EXISTS;
            return Result.error(msg);
        }
        return Result.error(MessageConstant.UNKNOWN_ERROR);
    }
}
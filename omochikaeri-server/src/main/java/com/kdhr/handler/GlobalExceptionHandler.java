package com.kdhr.handler;

import com.kdhr.constant.MessageConstant;
import com.kdhr.exception.BaseException;
import com.kdhr.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
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

    /**
     * 處理 SQL 異常 (包含 MySQL 和 PostgreSQL)
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(SQLException ex) {
        log.info("處理SQL例外 {}", ex.getMessage()); // 印出錯誤訊息就好，不要印整個 stack trace 嚇自己

        String message = ex.getMessage();

        // 1.標準 SQL 狀態碼 23505  為 "unique_violation"
        if ("23505".equals(ex.getSQLState())
        // 2.如果狀態碼沒抓到，再用字串判斷
        ||(ex.getMessage() != null && (message.contains("Duplicate entry") || message.contains("duplicate key value")))) {
            return Result.error(MessageConstant.ALREADY_EXISTS);
        }

        return Result.error(MessageConstant.UNKNOWN_ERROR);
    }
}
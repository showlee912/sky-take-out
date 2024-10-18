package com.sky.handler;

import com.sky.constant.MessageConstant;
import com.sky.exception.BaseException;
import com.sky.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex) {
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }


    /**
     * 处理 SQL 完整性约束违规异常。
     * 当数据库插入或更新操作违反了唯一性约束时调用此方法。
     *
     * @param ex 发生的 SQL 完整性约束违规异常
     * @return Result
     */
    @ExceptionHandler
    public Result exceptionHandler(SQLIntegrityConstraintViolationException ex) {

        // 获取异常消息
        String message = ex.getMessage();

        // 检查是否为重复条目异常
        if (message.contains("Duplicate entry")) {
            // 分割异常消息以获取重复的用户名
            String[] split = message.split(" ");
            String username = split[2];

            // 构造错误消息
            String msg = username + MessageConstant.ALREADY_EXISTS;
            // 返回带有自定义错误消息的结果
            return Result.error(msg);
        } else {
            // 对于其他类型的 SQL 完整性约束异常，返回通用错误信息
            return Result.error(MessageConstant.UNKNOWN_ERROR);
        }
    }

}

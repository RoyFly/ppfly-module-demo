package com.logger.controller;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import com.logger.service.LoggerService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 动态修改logback日志级别
 */
@RestController
@RequestMapping("/logger")
@Slf4j
public class LoggerController {

    @Autowired
    private LoggerService loggerService;

    /**
     * @param logLevel
     * @return
     */
    @GetMapping(value = "/level/{logLevel}")
    public String changeLogLevel(@PathVariable("logLevel") String logLevel) {
        try {
            // 1. 获取日志上下文
            LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
            // 2. 修改指定logger对象的日志级别(这里根据你自己的日志名称修改，我的叫"my_logger")
            // 如果修改mybatis、spring框架的日志，写"org.mybatis"、"org.springframework"
            loggerContext.getLogger("my_logger").setLevel(Level.valueOf(logLevel));
            log.info("日志级别修改成功!" + logLevel);
        } catch (Exception e) {
            log.error("动态修改日志级别出错", e);
            return "failure";
        }
        return "success";
    }

}

package com.isxcode.star.plugin.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

@Slf4j
class StarAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

    @Override
    public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {

        log.debug("线程异常");
        log.debug("异常信息：" + throwable.getMessage());
        log.info("线程名字：" + method.getName());
        for (Object param : objects) {
            log.info("参数值：" + param);
        }
    }
}

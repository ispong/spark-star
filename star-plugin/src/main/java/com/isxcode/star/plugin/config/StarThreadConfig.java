package com.isxcode.star.plugin.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Slf4j
@Configuration
@EnableAsync
public class StarThreadConfig implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {

        log.debug("初始化spring线程池");
        ThreadPoolTaskExecutor threadPoolTask = new ThreadPoolTaskExecutor();
        threadPoolTask.setCorePoolSize(10);
        threadPoolTask.setMaxPoolSize(Integer.MAX_VALUE);
        threadPoolTask.setQueueCapacity(Integer.MAX_VALUE);
        threadPoolTask.setThreadNamePrefix("StarThread-");
        threadPoolTask.setAllowCoreThreadTimeOut(true);
        threadPoolTask.setKeepAliveSeconds(60);
        threadPoolTask.setWaitForTasksToCompleteOnShutdown(true);
        threadPoolTask.setAwaitTerminationSeconds(0);
        threadPoolTask.initialize();
        return threadPoolTask;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {

        return new StarAsyncExceptionHandler();
    }
}


package com.isxcode.star.plugin.config;

import com.alibaba.fastjson.JSON;
import com.isxcode.star.common.constant.EventTypeConstants;
import com.isxcode.star.common.constant.KafkaConfigConstants;
import com.isxcode.star.common.pojo.dto.StarData;
import com.isxcode.star.common.properties.StarPluginProperties;
import com.isxcode.star.common.response.StarRequest;
import com.isxcode.star.common.response.StarResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.kafka.core.KafkaTemplate;

import java.lang.reflect.Method;
import java.util.Arrays;

@Slf4j
class StarAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final StarPluginProperties starPluginProperties;

    StarAsyncExceptionHandler(KafkaTemplate<String, String> kafkaTemplate, StarPluginProperties starPluginProperties) {
        this.kafkaTemplate = kafkaTemplate;
        this.starPluginProperties = starPluginProperties;
    }

    @Override
    public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {

        log.debug("线程异常");
        log.debug("异常信息：" + throwable.getMessage());
        log.debug("方法名字：" + method.getName());
        log.debug("请求参数:" + Arrays.toString(objects));
        StarRequest starRequest = JSON.parseObject((JSON.toJSONString(objects[0])), StarRequest.class);
        log.debug("请求体" + starRequest.toString());

        // 推送到kafka
        StarData starData = StarData.builder().eventType(EventTypeConstants.THREAD_ERROR_EVENT).build();
        StarResponse starResponse = new StarResponse("500", starRequest.getExecuteId() + "任务异常:" + throwable.getMessage(), starData);
        kafkaTemplate.send(KafkaConfigConstants.DEFAULT_TOPIC_NAME, starRequest.getExecuteId(), JSON.toJSONString(starResponse));
    }
}

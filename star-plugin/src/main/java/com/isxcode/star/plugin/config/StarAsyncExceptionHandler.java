package com.isxcode.star.plugin.config;

import com.alibaba.fastjson.JSON;
import com.isxcode.star.common.constant.KafkaConfigConstants;
import com.isxcode.star.common.properties.StarPluginProperties;
import com.isxcode.star.common.response.StarRequest;
import com.isxcode.star.common.response.StarResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.kafka.core.KafkaTemplate;

import java.lang.reflect.Method;

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
        StarRequest starRequest = JSON.parseObject((String) objects[0], StarRequest.class);
        log.debug("请求体" + starRequest.toString());

        // 推送到kafka
        StarResponse starResponse = new StarResponse("500", starRequest.getExecuteId() + "任务异常");
        kafkaTemplate.send(starPluginProperties.getKafkaConfig().get(KafkaConfigConstants.TOPIC_NAME), starRequest.getExecuteId(), JSON.toJSONString(starResponse));
    }
}

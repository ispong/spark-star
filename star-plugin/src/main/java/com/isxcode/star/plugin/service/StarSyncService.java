package com.isxcode.star.plugin.service;

import com.alibaba.fastjson.JSON;
import com.isxcode.star.common.constant.*;
import com.isxcode.star.common.exception.StarExceptionEnum;
import com.isxcode.star.common.pojo.dto.StarData;
import com.isxcode.star.common.properties.StarPluginProperties;
import com.isxcode.star.common.response.StarRequest;
import com.isxcode.star.common.response.StarResponse;
import com.isxcode.star.plugin.exception.StarException;
import lombok.extern.slf4j.Slf4j;
import org.apache.spark.launcher.SparkAppHandle;
import org.apache.spark.launcher.SparkLauncher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 所有的异步服务
 */
@Slf4j
@Service
public class StarSyncService {

    private final StarPluginProperties starPluginProperties;

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final StarService starService;

    public StarSyncService(StarPluginProperties starPluginProperties,
                           KafkaTemplate<String, String> kafkaTemplate,
                           StarService starService) {

        this.starPluginProperties = starPluginProperties;
        this.kafkaTemplate = kafkaTemplate;
        this.starService = starService;
    }

    @Async
    public void executeSyncWork(StarRequest starRequest, String url) {

        try {
            switch (url) {
                case UrlConstants.EXECUTE_URL:
                    execute(starRequest);
                    break;
//                case UrlConstants.EXECUTE_QUERY_URL:
//                    executeSql(starRequest);
//                    break;
//                case UrlConstants.EXECUTE_PAGE_QUERY_URL:
//                    executeSql(starRequest);
//                    break;
//                case UrlConstants.EXECUTE_MULTI_SQL_URL:
//                    executeSql(starRequest);
//                    break;
            }
        } catch (StarException e) {
            log.debug(e.getCode() + e.getMsg() + e.getMessage());
            StarResponse starResponse = new StarResponse(e.getCode(), e.getMsg());
            kafkaTemplate.send(KafkaConfigConstants.DEFAULT_TOPIC_NAME, starRequest.getExecuteId(), JSON.toJSONString(starResponse));
        }
    }

    public void execute(StarRequest starRequest) {

        if (starRequest.getExecuteId() == null ||
            starRequest.getSql() == null) {
            throw new StarException(StarExceptionEnum.REQUEST_VALUE_EMPTY);
        }

        SparkLauncher sparkLauncher = new SparkLauncher()
            .setMaster("yarn")
            .setDeployMode("cluster")
            .setAppName("star-test " + starRequest.getExecuteId())
            .setVerbose(true)
            .setMainClass(ExecutorMainClass.EXECUTE_MAIN_CLASS)
            .setAppResource("/home/dehoop/spark-star/star/plugins/star-executor.jar")
            .addJar("/home/dehoop/spark-star/star/lib/star-common.jar")
            .addJar("/home/dehoop/spark-star/star/lib/fastjson-1.2.79.jar")
            .setPropertiesFile("/home/dehoop/spark-star/star/conf/executor.conf")
            .addAppArgs(JSON.toJSONString(starRequest));

        try {
            sparkLauncher.startApplication(new SparkAppHandle.Listener() {

                @Override
                public void stateChanged(SparkAppHandle sparkAppHandle) {
                    StarData starData = StarData.builder().appId(sparkAppHandle.getAppId()).appState(sparkAppHandle.getState().toString()).eventType(EventTypeConstants.STATE_CHANGED_EVENT).build();
                    StarResponse starResponse = new StarResponse(MsgConstants.SUCCESS_CODE, MsgConstants.SUCCESS_RESPONSE_MSG, starData);
                    kafkaTemplate.send(KafkaConfigConstants.DEFAULT_TOPIC_NAME, starRequest.getExecuteId(), JSON.toJSONString(starResponse));
                }

                @Override
                public void infoChanged(SparkAppHandle sparkAppHandle) {
                    if (SparkAppState.CONNECTED.equals(sparkAppHandle.getState().toString())) {
                        StarData starData = StarData.builder().appId(sparkAppHandle.getAppId()).appState(sparkAppHandle.getState().toString()).eventType(EventTypeConstants.INFO_CHANGED_EVENT).build();
                        StarResponse starResponse = new StarResponse(MsgConstants.SUCCESS_CODE, MsgConstants.SUCCESS_RESPONSE_MSG, starData);
                        kafkaTemplate.send(KafkaConfigConstants.DEFAULT_TOPIC_NAME, starRequest.getExecuteId(), JSON.toJSONString(starResponse));
                    }
                }
            });
        } catch (IOException e) {
            log.debug(e.getMessage());
            throw new StarException(StarExceptionEnum.SPARK_LAUNCHER_ERROR);
        }
    }
}

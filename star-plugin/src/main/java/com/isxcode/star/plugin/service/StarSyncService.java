package com.isxcode.star.plugin.service;

import com.alibaba.fastjson.JSON;
import com.isxcode.star.common.constant.KafkaConfigConstants;
import com.isxcode.star.common.constant.MsgConstants;
import com.isxcode.star.common.constant.UrlConstants;
import com.isxcode.star.common.exception.StarExceptionEnum;
import com.isxcode.star.common.properties.StarPluginProperties;
import com.isxcode.star.common.response.StarRequest;
import com.isxcode.star.common.response.StarResponse;
import com.isxcode.star.plugin.exception.StarException;
import lombok.extern.slf4j.Slf4j;
import org.apache.spark.launcher.SparkLauncher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

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
                case UrlConstants.EXECUTE_SQL_URL:
                    executeSql(starRequest);
                    break;
                default:
            }
        } catch (StarException e) {
            log.debug(e.getCode() + e.getMsg() + e.getMessage());
            StarResponse starResponse = new StarResponse(e.getCode(), e.getMsg());
            kafkaTemplate.send(KafkaConfigConstants.DEFAULT_TOPIC_NAME, starRequest.getExecuteId(), JSON.toJSONString(starResponse));
        }
    }

    public void executeSql(StarRequest starRequest) {

        if (starRequest.getExecuteId() == null) {
            throw new StarException(StarExceptionEnum.REQUEST_VALUE_EMPTY);
        }

        log.debug("开始执行sparkSql");

        SparkLauncher sparkLauncher = new SparkLauncher()
            .setMaster(starPluginProperties.getMaster())
            .setAppName("spark-star app")
            .setDeployMode(starPluginProperties.getDeployMode())
            .setVerbose(true)
            .setMainClass("com.isxcode.star.Main")
            .setAppResource("../plugins/stat-executor.jar")
            .addAppArgs("");

        starPluginProperties.getSparkConfig().forEach(sparkLauncher::setConf);

        try {
            Process launch = sparkLauncher.launch();
            InputStream inputStream = launch.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String tmp;
            log.debug("========================> log");
            while ((tmp = bufferedReader.readLine()) != null) {
                log.debug(tmp);
            }
        } catch (IOException e) {
            log.debug(e.getMessage());
            throw new StarException(StarExceptionEnum.SPARK_LAUNCHER_ERROR);
        }
        log.debug("将结果推送kafka");
        StarResponse starResponse = new StarResponse("200", MsgConstants.SUCCESS_RESPONSE_MSG);
        kafkaTemplate.send(KafkaConfigConstants.DEFAULT_TOPIC_NAME, starRequest.getExecuteId(), JSON.toJSONString(starResponse));
    }

}

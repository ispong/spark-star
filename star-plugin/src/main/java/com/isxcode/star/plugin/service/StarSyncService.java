package com.isxcode.star.plugin.service;

import com.alibaba.fastjson.JSON;
import com.isxcode.star.common.constant.KafkaConfigConstants;
import com.isxcode.star.common.constant.MsgConstants;
import com.isxcode.star.common.constant.UrlConstants;
import com.isxcode.star.common.exception.StarExceptionEnum;
import com.isxcode.star.common.pojo.dto.StarData;
import com.isxcode.star.common.properties.StarPluginProperties;
import com.isxcode.star.common.response.StarRequest;
import com.isxcode.star.common.response.StarResponse;
import com.isxcode.star.plugin.exception.StarException;
import lombok.extern.slf4j.Slf4j;
import org.apache.spark.sql.SparkSession;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 所有的异步服务
 */
@Slf4j
@Service
public class StarSyncService {

    private final SparkSession sparkSession;

    private final StarPluginProperties starPluginProperties;

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final StarService starService;

    public StarSyncService(SparkSession sparkSession,
                           StarPluginProperties starPluginProperties,
                           KafkaTemplate<String, String> kafkaTemplate,
                           StarService starService) {

        this.sparkSession = sparkSession;
        this.starPluginProperties = starPluginProperties;
        this.kafkaTemplate = kafkaTemplate;
        this.starService = starService;
    }

    @Async
    public void executeSyncWork(StarRequest starRequest, String url) {

        try {
            switch (url) {
                case UrlConstants.EXECUTE_SQL_BY_KAFKA_URL:
                    executeSqlByKafka(starRequest);
                    break;
                case UrlConstants.EXECUTE_QUERY_SQL_BY_KAFKA_URL:
                    executeQuerySqlByKafka(starRequest);
                    break;
                default:
            }
        } catch (StarException e) {
            StarResponse starResponse = new StarResponse(e.getCode(), e.getMsg());
            kafkaTemplate.send(starPluginProperties.getKafkaConfig().get(KafkaConfigConstants.TOPIC_NAME), starRequest.getExecuteId(), JSON.toJSONString(starResponse));
        }
    }

    public void executeSqlByKafka(StarRequest starRequest) {

        if (starRequest.getExecuteId() == null) {
            throw new StarException(StarExceptionEnum.REQUEST_VALUE_EMPTY);
        }

        log.debug("开始执行sparkSql");
        sparkSession.sql(starRequest.getSql());
        log.debug("将结果推送kafka");

        StarResponse starResponse = new StarResponse("200", MsgConstants.SUCCESS_RESPONSE_MSG);
        kafkaTemplate.send(starPluginProperties.getKafkaConfig().get(KafkaConfigConstants.TOPIC_NAME), starRequest.getExecuteId(), JSON.toJSONString(starResponse));
    }

    public void executeQuerySqlByKafka(StarRequest starRequest) {

        if (starRequest.getExecuteId() == null) {
            throw new StarException(StarExceptionEnum.REQUEST_VALUE_EMPTY);
        }

        log.debug("sparkSql开始执行");
        StarData starData = starService.querySql(starRequest.getSql());
        log.debug("sparkSql执行成功" + starData.toString());
        StarResponse starResponse = StarResponse.builder().code("200").message(MsgConstants.SUCCESS_RESPONSE_MSG).starData(starData).build();
        kafkaTemplate.send(starPluginProperties.getKafkaConfig().get(KafkaConfigConstants.TOPIC_NAME), starRequest.getExecuteId(), JSON.toJSONString(starResponse));
    }
}

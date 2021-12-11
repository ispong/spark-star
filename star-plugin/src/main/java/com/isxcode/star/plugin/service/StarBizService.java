package com.isxcode.star.plugin.service;

import com.alibaba.fastjson.JSON;
import com.isxcode.star.common.pojo.dto.DataInfo;
import com.isxcode.star.common.pojo.entity.StarRequest;
import com.isxcode.star.common.pojo.entity.StarResponse;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StarBizService {

    private final SparkSession sparkSession;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public StarBizService(SparkSession sparkSession, KafkaTemplate<String, String> kafkaTemplate) {

        this.sparkSession = sparkSession;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendToKafka(String topic, String key, String value) {

        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, key, value);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> integerStringSendResult) {
                System.out.println("onSuccess" + integerStringSendResult.toString());
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("onFailure" + ex.getMessage());
            }
        });
    }

    @Async
    public void executeSql(StarRequest starRequest) {

        StarResponse starResponse = new StarResponse();

        // 执行sql
        Dataset<Row> rowDataset = sparkSession.sql(starRequest.getSql());

        if (!starRequest.isHasReturn()) {
            sendToKafka("star-kafka", starRequest.getExecuteId(), JSON.toJSONString(starResponse));
        }

        // 解析结果表
        DataInfo dataInfo = new DataInfo();

        // 添加表头
        String[] columns = rowDataset.columns();
        dataInfo.setColumnNames(Arrays.asList(columns));

        // 添加数据
        List<List<String>> dataList = new ArrayList<>();
        List<Row> rows = rowDataset.collectAsList();
        rows.forEach(e -> {
            List<String> metaData = new ArrayList<>();
            for (int i = 0; i < e.size(); i++) {
                metaData.add(String.valueOf(e.get(i)));
            }
            dataList.add(metaData);
        });
        dataInfo.setDataList(dataList);
        starResponse.setDataInfo(dataInfo);

        sendToKafka("star-kafka", starRequest.getExecuteId(), JSON.toJSONString(starResponse));
    }

}

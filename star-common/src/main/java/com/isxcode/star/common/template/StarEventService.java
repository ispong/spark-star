package com.isxcode.star.common.template;

import com.alibaba.fastjson.JSON;
import com.isxcode.star.common.constant.KafkaConfigConstants;
import com.isxcode.star.common.response.StarResponse;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

public class StarEventService {

    private final StarEventHandler starEventHandler;

    public StarEventService(StarEventHandler starEventHandler) {

        this.starEventHandler = starEventHandler;
    }

    @KafkaListener(topics = KafkaConfigConstants.DEFAULT_TOPIC_NAME)
    public void listen(ConsumerRecord<String, String> record) {

        String executeId = record.key();
        StarResponse starResponse = JSON.parseObject(record.value(), StarResponse.class);

        starEventHandler.subscribeEvent(executeId, starResponse);
    }
}

package com.isxcode.star.common.template;

import com.alibaba.fastjson.JSON;
import com.isxcode.star.common.constant.EventTypeConstants;
import com.isxcode.star.common.constant.KafkaConfigConstants;
import com.isxcode.star.common.response.StarResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
public class StarEventService {

    private final StarEventHandler starEventHandler;

    public StarEventService(StarEventHandler starEventHandler) {

        this.starEventHandler = starEventHandler;
    }

    @KafkaListener(topics = KafkaConfigConstants.DEFAULT_TOPIC_NAME)
    public void listen(ConsumerRecord<String, String> record) {

        String executeId = record.key();
        StarResponse starResponse = JSON.parseObject(record.value(), StarResponse.class);
        log.debug("监听到的返回数据 executeId：" + executeId + "starResponse:" + starResponse.toString());

        switch (starResponse.getStarData().getEventType()) {

            case EventTypeConstants.QUERY_RESULT_EVENT:
                starEventHandler.queryResultEvent(executeId, starResponse);
                break;
            case EventTypeConstants.STATE_CHANGED_EVENT:
                starEventHandler.stateChangedEvent(executeId, starResponse);
                break;
            case EventTypeConstants.INFO_CHANGED_EVENT:
                starEventHandler.infoChangedEvent(executeId, starResponse);
                break;
        }
    }
}

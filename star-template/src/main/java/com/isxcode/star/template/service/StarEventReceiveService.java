package com.isxcode.star.template.service;

import com.isxcode.star.common.response.StarResponse;
import com.isxcode.star.common.template.StarEventHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StarEventReceiveService implements StarEventHandler {

    @Override
    public void queryResultEvent(String executeId, StarResponse starResponse) {

        log.info("执行id:" + executeId + "获得返回结果:" + starResponse);
    }

    @Override
    public void stateChangedEvent(String executeId, StarResponse starResponse) {
        log.info("执行id:" + executeId + "当前状态:" + starResponse);
    }

    @Override
    public void infoChangedEvent(String executeId, StarResponse starResponse) {
        log.info("执行id:" + executeId + "app相关信息:" + starResponse);
    }

    @Override
    public void threadErrorEvent(String executeId, StarResponse starResponse) {
        log.info("执行id:" + executeId + "异常信息:" + starResponse);
    }
}

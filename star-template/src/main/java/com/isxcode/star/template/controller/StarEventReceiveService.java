package com.isxcode.star.template.controller;

import com.isxcode.star.common.response.StarResponse;
import com.isxcode.star.common.template.StarEventHandler;
import org.springframework.stereotype.Service;

@Service
public class StarEventReceiveService implements StarEventHandler {

    @Override
    public void subscribeEvent(String executeId, StarResponse starResponse) {

        System.out.println("打印starResponse" + starResponse.toString());
    }
}

package com.isxcode.star.common.template;

import com.isxcode.star.common.response.StarResponse;
import org.springframework.stereotype.Service;

@Service
public interface StarEventHandler {

    default void subscribeEvent(String executeId, StarResponse starResponse) {

    }
}

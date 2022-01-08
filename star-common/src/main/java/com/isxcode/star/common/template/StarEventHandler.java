package com.isxcode.star.common.template;

import com.isxcode.star.common.response.StarResponse;

public interface StarEventHandler {

    default void subscribeEvent(String executeId, StarResponse starResponse) {

    }
}

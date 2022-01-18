package com.isxcode.star.common.template;

import com.isxcode.star.common.response.StarResponse;

public interface StarEventHandler {

    default void queryResultEvent(String executeId, StarResponse starResponse) {

    }

    default void stateChangedEvent(String executeId, StarResponse starResponse) {

    }

    default void infoChangedEvent(String executeId, StarResponse starResponse) {

    }

    default void threadErrorEvent(String executeId, StarResponse starResponse) {

    }
}

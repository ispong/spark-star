package com.isxcode.star.plugin.response;

import com.isxcode.star.common.response.StarResponse;
import lombok.Getter;
import lombok.Setter;

public class SuccessException extends RuntimeException {

    @Setter
    @Getter
    private StarResponse baseResponse;

    public SuccessException(StarResponse baseResponse) {

        this.baseResponse = baseResponse;
    }
}

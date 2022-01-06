package com.isxcode.star.plugin.exception;

import com.isxcode.star.common.exception.StarExceptionEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StarException extends RuntimeException {

    private final String code;

    private final String msg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public StarException(String msg, String code) {

        this.code = code;
        this.msg = msg;
    }

    public StarException(StarExceptionEnum starExceptionEnum) {

        this.code = starExceptionEnum.getCode();
        this.msg = starExceptionEnum.getMessage();
    }
}

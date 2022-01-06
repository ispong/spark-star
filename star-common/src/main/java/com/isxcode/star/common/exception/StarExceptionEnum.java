package com.isxcode.star.common.exception;

import lombok.Getter;

/**
 * 所有返回异常
 */
public enum StarExceptionEnum {

    EXECUTE_ERROR("50001", "执行异常"),
    ;

    @Getter
    private final String code;

    @Getter
    private final String message;

    StarExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}

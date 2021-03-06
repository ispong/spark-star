package com.isxcode.star.common.exception;

import lombok.Getter;

/**
 * 所有返回异常
 */
public enum StarExceptionEnum {

    KEY_IS_NULL("50001", "key为null"),

    KEY_IS_ERROR("50002", "key不正确"),

    REQUEST_VALUE_EMPTY("50003", "缺少输入参数"),

    SPARK_LAUNCHER_ERROR("50004", "spark发布错误"),

    COMMAND_EXECUTE_ERROR("50005", "命令运行异常"),
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

package com.isxcode.star.common.menu;

import lombok.Getter;

public enum ResponseEnum {

    EXECUTE_SUCCESS("200", "执行成功"),

    REMOTE_ERROR("50001", "远程调用失败"),;

    @Getter
    private final String code;

    @Getter
    private final String message;

    ResponseEnum(String code, String message) {
        this.code = code;
        this.message = message;
    };
}

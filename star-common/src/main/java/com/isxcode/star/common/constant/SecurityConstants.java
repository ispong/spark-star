package com.isxcode.star.common.constant;

public interface SecurityConstants {

    /**
     * 请求头参数
     */
    String HEADER_AUTHORIZATION = "starkey";

    /**
     * token为null跳转路径
     */
    String TOKEN_IS_NULL_PATH = "/exception/tokenIsNull";

    /**
     * 权限不足跳转路径
     */
    String AUTH_ERROR_PATH = "/exception/authError";
}

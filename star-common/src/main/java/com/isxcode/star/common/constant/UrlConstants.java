package com.isxcode.star.common.constant;

/**
 * 所有请求路径
 */
public interface UrlConstants {

    /**
     * 基础路径
     */
    String BASE_URL = "http://%s:%s/spark-star";

    /**
     * 直接执行sql
     */
    String EXECUTE_SQL_URL = "/executeSql";

    /**
     * 查询spark的日志
     */
    String GET_JOB_LOG_URL = "/getJobLog";

    /**
     * 停止作业
     */
    String STOP_JOB_URL = "/stopJob";
}

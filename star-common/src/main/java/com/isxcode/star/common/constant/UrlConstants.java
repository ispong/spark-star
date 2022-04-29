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
     * 直接执行sql 无返回结果
     */
    String EXECUTE_URL = "/execute";

    /**
     * 执行sql 有返回结果
     */
    String EXECUTE_QUERY_URL = "/executeQuery";

    /**
     * 执行sql 分页返回结果
     */
    String EXECUTE_PAGE_QUERY_URL = "/executePageQuery";

    /**
     * 执行多条sql 无返回结果
     */
    String EXECUTE_MULTI_SQL_URL = "/executeMultiSql";

    /**
     * 查询spark的日志
     */
    String GET_JOB_LOG_URL = "/getJobLog";

    /**
     * 停止作业
     */
    String STOP_JOB_URL = "/stopJob";

    /**
     * 执行sql 有返回结果
     */
    String QUICK_EXECUTE_QUERY_URL = "/quick/executeQuery";
}

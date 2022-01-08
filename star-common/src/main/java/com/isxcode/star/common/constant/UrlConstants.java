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
     * 直接执行sql 通过kafka事件推送
     */
    String EXECUTE_SQL_BY_KAFKA_URL = "/executeSqlByKafka";

    /**
     * 直接执行返回sql 通过kafka事件推送
     */
    String EXECUTE_QUERY_SQL_BY_KAFKA_URL = "/executeQuerySqlByKafka";

    /**
     * 执行sql并返回查询结果
     */
    String EXECUTE_QUERY_SQL_URL = "/executeQuerySql";

    /**
     * 执行sql并分页返回结果
     */
    String EXECUTE_PAGE_QUERY_SQL_URL = "/executePageQuerySql";

    /**
     * 获取某张表的数据
     */
    String GET_TABLE_DATA_URL = "/getTableData";
}

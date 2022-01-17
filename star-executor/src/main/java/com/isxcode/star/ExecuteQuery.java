package com.isxcode.star;

import com.alibaba.fastjson.JSON;
import com.isxcode.star.common.response.StarRequest;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;

public class ExecuteQuery {

    public static void main(String[] args) {

        // 获取请求参数
        StarRequest starRequest = JSON.parseObject(String.valueOf(args[0]), StarRequest.class);
        System.out.println("显示请求参数：" + starRequest.toString());

        // 构建新的sparkSession
        SparkSession sparkSession = SparkSession.builder().getOrCreate();

        // 解析sql
        String sql = "select * from ( " + starRequest.getSql() + " ) limit " + starRequest.getLimit();
        System.out.println("执行sql" + sql);

        // 切换数据源
        sparkSession.sql("use " + starRequest.getDatabase());

        // 执行查询sql
        Dataset<Row> rowDataset = sparkSession.sql(sql);

        // 将结果写入临时数据库
        rowDataset.write()
            .format("hive")
            .mode(SaveMode.Append)
            .saveAsTable("star_tmp." + starRequest.getExecuteId());

        // 结束会话
        sparkSession.stop();
    }
}

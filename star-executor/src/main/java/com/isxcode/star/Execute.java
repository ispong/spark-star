package com.isxcode.star;

import com.alibaba.fastjson.JSON;
import com.isxcode.star.common.response.StarRequest;
import org.apache.spark.sql.SparkSession;

public class Execute {

    public static void main(String[] args) {

        // 获取请求参数
        StarRequest starRequest = JSON.parseObject(String.valueOf(args[0]), StarRequest.class);
        System.out.println("显示请求参数：" + starRequest.toString());

        // 构建新的sparkSession
        SparkSession sparkSession = SparkSession.builder()
            .config("hive.metastore.uris", "thrift://main:9083")
            .config("spark.driver.memory", "1g")
            .config("spark.executor.memory", "2g")
            .config("spark.sql.storeAssignmentPolicy", "LEGACY")
            .enableHiveSupport()
            .getOrCreate();

        // 执行spark
        System.out.println("开始执行");
        sparkSession.sql(starRequest.getSql());

        // 停止session
        sparkSession.stop();
    }
}

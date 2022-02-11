package com.isxcode.star.demo2;

import org.apache.spark.sql.SparkSession;

public class Demo {

    public static void main(String[] args) {

        // 构建新的sparkSession
        SparkSession sparkSession = SparkSession.builder()
            .config("hive.metastore.uris", "thrift://localhost:9083")
            .config("spark.driver.memory", "1g")
            .config("spark.executor.memory", "2g")
            .config("spark.sql.storeAssignmentPolicy", "LEGACY")
            .enableHiveSupport()
            .getOrCreate();

        // 执行spark
        System.out.println("开始执行");
        sparkSession.sql("select * from default.userinfo limit 100");

        // 停止session
        sparkSession.stop();
    }
}

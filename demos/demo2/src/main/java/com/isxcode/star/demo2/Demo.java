package com.isxcode.star.demo2;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class Demo {

    public static void main(String[] args) {

        // 构建新的sparkSession
        SparkSession sparkSession = SparkSession.builder()
            .master("yarn")
            .appName("demo2")
            .config("deploy-mode", "cluster")
            .config("spark.yarn.queue", "default")
            .config("hive.metastore.uris", "thrift://localhost:9083")
            .config("spark.driver.memory", "2g")
            .config("spark.num.executors", 4)  // 一个作业设置多少个executor
            .config("spark.executor.memory", "500m") // executor一共可申请内存  1g 500m
            .config("spark.executor.cores", 2) // 每个executor可申请内核
            .config("spark.yarn.historyServer.allowTracking", true) // 允许historyServer监控
            .config("spark.sql.storeAssignmentPolicy", "LEGACY")
            .enableHiveSupport()
            .getOrCreate();

        // 执行spark
        System.out.println("开始执行");
        Dataset<Row> sql = sparkSession.sql("select count(1) from default.userinfo where username like '平%' and school in ('高中','初中')");
        System.out.println("开始结束");
        sql.show();

        // 停止session
        sparkSession.stop();
    }
}

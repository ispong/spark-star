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
            .config("spark.driver.memory", "1g")
            .config("spark.executor.memory", "2g")
            .config("spark.executor.cores", "2g")
            .config("spark.yarn.historyServer.allowTracking", true)
            .config("spark.sql.storeAssignmentPolicy", "LEGACY")
            .enableHiveSupport()
            .getOrCreate();

        // 执行spark
        System.out.println("开始执行");
        Dataset<Row> sql = sparkSession.sql("select count(1) from default.userinfo");
        sql.show();

        // 停止session
        sparkSession.stop();
    }
}

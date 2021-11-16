package com.isxcode.demo1.config;

import org.apache.spark.sql.SparkSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SparkConfig {

    @Bean("SparkSession")
    public SparkSession sparkBean() {

        // 设置master类型
        String master = "yarn";

        // 创建sparkSession
        return  SparkSession
                .builder()
                .appName("ispong-hive-demo")
                .master(master)
                .config("hive.metastore.uris", "thrift://172.23.39.206:30123")
                .config("spark.sql.hive.metastore.version", "2.1.1")
                .config("spark.sql.hive.metastore.jars", "/data/cdh/cloudera/parcels/CDH/lib/hive/lib/*")
                .enableHiveSupport()
                .getOrCreate();
    }
}


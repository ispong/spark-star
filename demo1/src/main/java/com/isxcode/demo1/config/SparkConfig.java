package com.isxcode.demo1.config;

import org.apache.spark.sql.SparkSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SparkConfig {

    @Bean("SparkSession")
    public SparkSession sparkBean() {

        return SparkSession
                .builder()
                .appName("ispong spark demo")
                .master("yarn")
                .config("hive.metastore.uris", "thrift://172.23.39.206:30123")
                .enableHiveSupport()
                .getOrCreate();
    }
}


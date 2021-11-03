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
                .appName("isxcode spark demo1")
                .master("yarn")
                .config("hive.metastore.uris", "thrift://8.142.142.196:9083")
                .enableHiveSupport()
                .getOrCreate();
    }
}


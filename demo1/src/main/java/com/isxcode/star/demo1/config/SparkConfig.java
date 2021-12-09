package com.isxcode.star.demo1.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.spark.sql.SparkSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class SparkConfig {

    @Bean("SparkSession")
    public SparkSession sparkBean() {

        log.info("初始化sparkSession");
        return SparkSession
                .builder()
                .appName("spark star demo1")
                .master("yarn")
                .config("spark.ui.port", "30157")
                .config("hive.metastore.uris", "thrift://dcloud-dev:30123")
                .enableHiveSupport()
                .getOrCreate();
    }
}
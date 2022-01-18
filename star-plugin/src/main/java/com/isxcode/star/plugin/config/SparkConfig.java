package com.isxcode.star.plugin.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.spark.sql.SparkSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class SparkConfig {

    @Bean("sparkSession")
    public SparkSession sparkSession() {

        log.debug("初始化sparkSession");
        SparkSession.Builder sparkBuilder = SparkSession
            .builder()
            .appName("spark-star plugin")
            .master("yarn")
            .config("spark.executor.memory", "1g")
            .config("spark.executor.cores", "1")
            .config("spark.driver.memory", "1g")
            .config("spark.num.executors", "1");

        return sparkBuilder.getOrCreate();
    }
}

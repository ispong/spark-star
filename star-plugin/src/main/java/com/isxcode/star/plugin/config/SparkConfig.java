package com.isxcode.star.plugin.config;

import com.isxcode.star.common.properties.StarPluginProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.spark.sql.SparkSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class SparkConfig {

    private final StarPluginProperties starPluginProperties;

    public SparkConfig(StarPluginProperties starPluginProperties) {

        this.starPluginProperties = starPluginProperties;
    }

    @Bean("sparkSession")
    public SparkSession sparkSession() {

        log.info("初始化sparkSession");
        SparkSession.Builder sparkBuilder = SparkSession
            .builder()
            .appName(starPluginProperties.getAppName())
            .master(starPluginProperties.getMaster());

        starPluginProperties.getSparkConfig().forEach(sparkBuilder::config);

        return sparkBuilder.enableHiveSupport().getOrCreate();
    }
}

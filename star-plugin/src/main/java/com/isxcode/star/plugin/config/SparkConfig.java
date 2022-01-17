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

        log.debug("初始化sparkSession");
        SparkSession.Builder sparkBuilder = SparkSession
            .builder()
            .appName(starPluginProperties.getAppNamePrefix() + "plugin")
            .master(starPluginProperties.getMaster());

        log.debug("SparkConfig.sparkSession.starPluginProperties:" + starPluginProperties.getSparkConfig().toString());
        starPluginProperties.getSparkConfig().forEach(sparkBuilder::config);

        return sparkBuilder.enableHiveSupport().getOrCreate();
    }
}

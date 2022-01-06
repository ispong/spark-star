package com.isxcode.star.plugin.config;

import com.isxcode.star.common.properties.StarPluginProperties;
import org.apache.spark.sql.SparkSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SparkConfig {

    public final StarPluginProperties starPluginProperties;

    public SparkConfig(StarPluginProperties starPluginProperties) {

        this.starPluginProperties = starPluginProperties;
    }

    @Bean
    public SparkSession sparkSession() {

        return SparkSession
                .builder()
                .appName(starPluginProperties.getAppName())
                .master(starPluginProperties.getMaster())
                .config("spark.ui.port", starPluginProperties.getSparkUiPort())
                .config("hive.metastore.uris", starPluginProperties.getHiveMetastoreUris())
                .enableHiveSupport()
                .getOrCreate();
    }
}

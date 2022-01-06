package com.isxcode.star.demo1.config;

import com.isxcode.star.demo1.properties.DemoProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.spark.sql.SparkSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class SparkConfig {

    private final DemoProperties demoProperties;

    public SparkConfig(DemoProperties demoProperties) {
        this.demoProperties = demoProperties;
    }

    @Bean("SparkSession")
    public SparkSession sparkBean() {

        log.info("初始化sparkSession");
        SparkSession.Builder sparkBuilder = SparkSession
                .builder()
                .appName(demoProperties.getAppName())
                .master(demoProperties.getMaster());

        demoProperties.getSparkConfig().forEach(sparkBuilder::config);

        return sparkBuilder.enableHiveSupport().getOrCreate();
    }
}

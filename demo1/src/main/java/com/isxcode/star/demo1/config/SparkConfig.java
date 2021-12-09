package com.isxcode.star.demo1.config;

import com.isxcode.star.demo1.properties.DemoProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.spark.sql.SparkSession;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;

@Slf4j
@Configuration
public class SparkConfig {

    private final DemoProperties demoProperties;

    public SparkConfig(DemoProperties demoProperties) {
        this.demoProperties = demoProperties;
    }

    @Bean("initYarn")
    public void initYarnEnv() {

        log.info("初始化项目yarn环境");
        Resource coreResource = new ClassPathResource(demoProperties.getCoreSitePath());
        Resource hdfsResource = new ClassPathResource(demoProperties.getHdfsSitePath());
        Resource mapredResource = new ClassPathResource(demoProperties.getMapredSitePath());
        Resource yarnResource = new ClassPathResource(demoProperties.getYarnSitePath());
        try {
            PropertiesLoaderUtils.loadProperties(coreResource);
            PropertiesLoaderUtils.loadProperties(hdfsResource);
            PropertiesLoaderUtils.loadProperties(mapredResource);
            PropertiesLoaderUtils.loadProperties(yarnResource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ConditionalOnBean(name = "initYarn")
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

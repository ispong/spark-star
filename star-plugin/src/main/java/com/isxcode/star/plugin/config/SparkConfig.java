package com.isxcode.star.plugin.config;

import com.isxcode.star.plugin.properties.StarProperties;
import org.apache.spark.sql.SparkSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SparkConfig {

    public final StarProperties starProperties;

    public SparkConfig(StarProperties starProperties) {
        this.starProperties = starProperties;
    }

    @Bean
    public SparkSession sparkSession() {

        return SparkSession
                .builder()
                .master(starProperties.getMaster())
                .config("spark.yarn.preserve.staging.files", true)
                .config("hive.metastore.uris", starProperties.getHiveMetastoreUris())
                .config("spark.sql.hive.metastore.version", starProperties.getHiveMetastoreVersion())
                .config("spark.sql.hive.metastore.jars", starProperties.getHiveMetastoreJars())
                .config("spark.yarn.archive", starProperties.getSparkYarnArchive())
                .enableHiveSupport()
                .getOrCreate();
    }
}

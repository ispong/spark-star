package com.isxcode.star.plugin.service;

import com.isxcode.star.plugin.properties.StarProperties;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Service;

@Service
public class StarServiceImpl implements StarService {

    private final StarProperties starProperties;

    public StarServiceImpl(StarProperties starProperties) {
        this.starProperties = starProperties;
    }

    @Override
    public SparkSession generateNewSession() {

        return SparkSession
                .builder()
                .master(starProperties.getMaster())
                .config("hive.metastore.uris", starProperties.getHiveMetastoreUris())
                .config("spark.sql.hive.metastore.version", starProperties.getHiveMetastoreVersion())
                .config("spark.sql.hive.metastore.jars", starProperties.getHiveMetastoreJars())
                .enableHiveSupport()
                .getOrCreate();
    }
}

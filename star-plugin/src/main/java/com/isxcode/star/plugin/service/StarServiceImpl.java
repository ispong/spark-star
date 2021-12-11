package com.isxcode.star.plugin.service;

import com.isxcode.star.common.properties.StarProperties;
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
                .appName("star spark session")
                .master("yarn-client")
                .config("hive.metastore.uris", starProperties.getHiveMetastoreUris())
                .enableHiveSupport()
                .getOrCreate();
    }
}

package com.isxcode.star.plugin.service;

import com.isxcode.star.common.properties.StarPluginProperties;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Service;

@Service
public class StarServiceImpl implements StarService {

    private final StarPluginProperties starPluginProperties;

    public StarServiceImpl(StarPluginProperties starPluginProperties) {
        this.starPluginProperties = starPluginProperties;
    }

    @Override
    public SparkSession generateNewSession() {

        return SparkSession
                .builder()
                .appName("star spark session")
                .master("yarn-client")
                .config("hive.metastore.uris", starPluginProperties.getHiveMetastoreUris())
                .enableHiveSupport()
                .getOrCreate();
    }
}

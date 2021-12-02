package com.isxcode.star.plugin.service;

import org.apache.spark.sql.SparkSession;

public class StarServiceImpl implements StarService {

    @Override
    public SparkSession generateNewSession() {

        return SparkSession
                .builder()
                .appName("ispong-hive-demo")
                .master("yarn")
                .config("hive.metastore.uris", "thrift://172.23.39.206:30123")
                .config("spark.sql.hive.metastore.version", "2.1.1")
                .config("spark.sql.hive.metastore.jars", "/data/cdh/cloudera/parcels/CDH/lib/hive/lib/*")
                .enableHiveSupport()
                .getOrCreate();
    }
}

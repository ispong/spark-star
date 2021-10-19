package com.isxcode.demo.config;

import org.apache.spark.sql.SparkSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SparkConfig {

    @Bean("SparkSession")
    public SparkSession sparkBean() {

        // hadoop fs -mkdir /spark-jars
        // hadoop fs -put /data/cdh/cloudera/parcels/CDH/lib/spark/spark-jars/* hdfs://172.23.39.206:30116/spark-jars/
        return SparkSession
                .builder()
                .appName("ispong spark demo")
//                .master("local")
                .master("yarn")
                .config("spark.yarn.jars", "hdfs://172.23.39.206:30116/spark-jars/*.jar")
                .config("hive.metastore.uris", "thrift://172.23.39.206:30123")
                .enableHiveSupport()
                .getOrCreate();

    }
}


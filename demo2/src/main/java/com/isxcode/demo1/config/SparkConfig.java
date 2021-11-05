package com.isxcode.demo1.config;

import org.apache.spark.sql.SparkSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SparkConfig {

    @Bean("SparkSession")
    public SparkSession sparkBean() {

        // 配置spark.yarn.archive
        // cd /data/cdh/cloudera/parcels/CDH/lib/spark/jars
        // zip -q -r spark_jars.zip *
        // hadoop fs -mkdir -p /spark-yarn/zip
        // hadoop fs -put spark_jars.zip /spark-yarn/zip/
        return SparkSession
                .builder()
                .appName("ispong spark demo")
//                .master("local")
                .master("yarn")
                .config("spark.yarn.archive", "hdfs://172.23.39.206:30116/spark-yarn/zip/spark_jars.zip")
                .config("hive.metastore.uris", "thrift://172.23.39.206:30123")
                .enableHiveSupport()
                .getOrCreate();
    }

}


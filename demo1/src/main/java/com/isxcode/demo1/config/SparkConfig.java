package com.isxcode.demo1.config;

import org.apache.spark.sql.SparkSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SparkConfig {

    @Bean("SparkSession")
    public SparkSession sparkBean() {

        String master = "172.26.34.162";
        String slave1 = "172.26.34.161";
        String slave2 = "172.16.215.85";
        String slave3 = "172.16.215.84";
        String slave4 = "172.16.215.83";

        // hadoop fs -mkdir /spark-jars
        // hadoop fs -put /opt/spark/jars/* hdfs://ispongcluster/spark-jars/
        return SparkSession
                .builder()
                .appName("isxcode spark demo1")
                .master("yarn")
                .config("hive.metastore.uris", "thrift://master:9083")
                .config("spark.yarn.jars", "hdfs://" + slave1 + ":8020/spark-jars/*.jar")
                .config("spark.hadoop.yarn.resourcemanager.address", slave1 + ":8032")
                .config("spark.hadoop.yarn.resourcemanager.scheduler.address", slave1 + ":8030")
                .enableHiveSupport()
                .getOrCreate();
    }
}

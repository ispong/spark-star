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

        // 配置spark.yarn.archive
        // cd /opt/spark/jars/
        // zip -q -r spark_jars.zip *
        // hadoop fs -mkdir -p /spark-yarn/zip
        // hadoop fs -put spark_jars.zip /spark-yarn/zip/
        return SparkSession
                .builder()
                .appName("isxcode spark demo1")
//                .master("yarn")
                .master("spark://master:7077")
                .config("hive.metastore.uris", "thrift://master:9083")
//                .config("spark.yarn.preserve.staging.files", true)
//                .config("spark.yarn.archive", "hdfs://" + slave2 + ":8020/spark-yarn/zip/spark_jars.zip")
//                .config("spark.hadoop.yarn.resourcemanager.address", slave1 + ":8032")
//                .config("spark.hadoop.yarn.resourcemanager.scheduler.address", slave1 + ":8030")
                .enableHiveSupport()
                .getOrCreate();
    }
}

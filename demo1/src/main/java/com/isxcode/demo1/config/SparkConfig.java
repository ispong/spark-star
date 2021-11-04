package com.isxcode.demo1.config;

import org.apache.spark.sql.SparkSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SparkConfig {

    @Bean("SparkSession")
    public SparkSession sparkBean() {

        // 配置spark.yarn.archive
        // cd /opt/spark/jars/
        // zip -q -r spark_jars.zip *
        // hadoop fs -mkdir -p /spark-yarn/zip
        // hadoop fs -put spark_jars.zip /spark-yarn/zip/
        SparkSession sparkSession = SparkSession
                .builder()
                .appName("isxcode spark demo1")
                .master("yarn")
//                .master("spark://master:7077")
//                .master("local")
                .config("spark.yarn.preserve.staging.files", true)
                .config("spark.yarn.archive", "hdfs://ispongcluster/spark-yarn/zip/spark_jars.zip")
                .config("spark.hadoop.yarn.resourcemanager.address", "slave1:8032")
                .config("spark.hadoop.yarn.resourcemanager.scheduler.address", "slave1:8030")
                .config("hive.metastore.uris", "thrift://master:9083")
                .config("spark.files", "/opt/spark/conf/hdfs-site.xml,/opt/spark/conf/core-site.xml,/opt/spark/conf/yarn-site.xml")
                .enableHiveSupport()
                .getOrCreate();

        return sparkSession;

//        spark.sql.warehouse.dir
//        hive.metastore.warehouse.dir
    }
}

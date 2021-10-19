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
                .master("local")
//                .master("yarn")
//                .config("hive.metastore.uris", "thrift://172.23.39.206:30123")
//                .config("spark.yarn.jars", "hdfs://172.23.39.206:30116/spark-jars/**")
                .enableHiveSupport()
                .getOrCreate();

//        return SparkSession
//                .builder()
//                .appName("ispong demo")
////                .master("yarn")
//                .config("spark.master", "yarn")
//                .config("spark.yarn.jars", "/home/dehoop/jars/*")
//                .config("hive.metastore.uris", "thrift://172.23.39.206:30123")
//                .config("spark.sql.hive.metastore.version", "2.1.1")
//                .config("spark.sql.hive.metastore.jars", "/opt/cloudera/parcels/CDH-6.2.0-1.cdh6.2.0.p0.967373/lib/hive/lib/*")
//                .enableHiveSupport()
//                .getOrCreate();
//                .config("spark.master", "spark://172.23.39.206:7077")
//                .config("spark.deploy-mode", "client")
//              .config("spark.ui.port", "30128") // 没用
//                .config("spark.yarn.jars", "/home/dehoop/jars/*") // 必须
//                .config("spark.hadoop.fs.defaultFS", "hdfs://172.23.39.206:30116")
//                .config("spark.hadoop.yarn.resourcemanager.address", "172.23.39.206:8032")
//                .config("spark.hadoop.yarn.nodemanager.address", "172.23.39.206:8041")
//                .config("hive.metastore.uris", "thrift://172.23.39.206:30123")
//                .config("spark.sql.hive.metastore.version", "2.1.1") // 必须
//                .config("spark.sql.hive.metastore.jars", "/opt/cloudera/parcels/CDH-6.2.0-1.cdh6.2.0.p0.967373/lib/hive/lib/*") // 必须
//                .enableHiveSupport() // 必须
//                .getOrCreate();

//        .config("spark.driver.allowMultipleContexts", "true")
//                .config("spark.shuffle.service.enabled", "false")
//                .config("spark.dynamicAllocation.enabled", "false")
//                .config("spark.executor.memory", "512m")
//                .config("spark.executor.instances", "1")
//                .config("spark.executor.cores", "2")
//                .config("spark.driver.memory", "2g")
//                .config("spark.sql.broadcastTimeout", "36000")
//                .config("spark.sql.autoBroadcastJoinThreshold", "-1")
//                .config("spark.driver.cores", "1")
    }
}


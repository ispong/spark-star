package com.isxcode.star.plugin.config;

import com.isxcode.star.plugin.properties.StarProperties;
import org.apache.spark.sql.SparkSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SparkConfig {

    public final StarProperties starProperties;

    public SparkConfig(StarProperties starProperties) {
        this.starProperties = starProperties;
    }

    @Bean
    public SparkSession sparkSession() {

        return SparkSession
                .builder()
                .appName("star spark session")
                .master("yarn-client")
                .config("spark.yarn.preserve.staging.files", true)
//                .config("spark.yarn.archive", "hdfs://ispongcluster/spark-yarn/zip/spark_jars.zip")
//                .config("spark.hadoop.yarn.resourcemanager.address", "172.23.39.206:8032")
//                .config("spark.files", "/data/cdh/cloudera/parcels/CDH/lib/hadoop/etc/hadoop/hdfs-site.xml,/data/cdh/cloudera/parcels/CDH/lib/hadoop/etc/hadoop/core-site.xml,/data/cdh/cloudera/parcels/CDH/lib/hadoop/etc/hadoop/yarn-site.xml")
//                .config("spark.hadoop.yarn.resourcemanager.scheduler.address", "slave1:8030")
//                .config("hive.metastore.uris", "thrift://master:9083")
//                .config("hive.metastore.uris", starProperties.getHiveMetastoreUris())
//                .config("spark.yarn.archive", starProperties.getSparkYarnArchi`ve())
//                .enableHiveSupport()
                .getOrCreate();
    }
}

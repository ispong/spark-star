package com.isxcode.star;

import org.apache.spark.sql.SparkSession;

public class Main {

    public static void main(String[] args) {

        SparkSession spark = SparkSession.builder()
            .appName("")
            .master("")
            .config("spark.ui.port", "30157")
            .config("hive.metastore.uris","thrift://dcloud-dev:30123")
            .config("spark.driver.memory","1g")
            .config("spark.executor.memory","2g")
            .config("spark.sql.storeAssignmentPolicy","LEGACY")
            .getOrCreate();

        spark.sql("select * from rd_dev.ispong_table").show();

        spark.stop();
    }
}

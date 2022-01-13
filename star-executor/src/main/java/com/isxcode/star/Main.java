package com.isxcode.star;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;

public class Main {

    public static void main(String[] args) {

        SparkSession spark = SparkSession.builder()
            .appName("star app")
            .master("yarn")
            .config("spark.ui.port", "30157")
            .config("hive.metastore.uris","thrift://dcloud-dev:30123")
            .config("spark.driver.memory","1g")
            .config("spark.executor.memory","2g")
            .config("spark.sql.storeAssignmentPolicy","LEGACY")
            .getOrCreate();

        Dataset<Row> rowDataset = spark.sql("select * from rd_dev.ispong_table");

        rowDataset.write()
            .format("hive")
            .mode(SaveMode.Append)
            .saveAsTable("tmp.ispong_table");

        spark.stop();
    }
}

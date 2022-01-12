package com.isxcode.star;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        String args1 = args[0];
        System.out.println(args1);

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

        String[] columns = rowDataset.columns();
        System.out.println("===>column:" + Arrays.toString(columns));

        List<List<String>> dataList = new ArrayList<>();
        List<Row> rows = rowDataset.collectAsList();
        rows.forEach(e -> {
            List<String> metaData = new ArrayList<>();
            for (int i = 0; i < e.size(); i++) {
                metaData.add(String.valueOf(e.get(i)));
            }
            dataList.add(metaData);
        });

        dataList.forEach(e -> {
            for (String s : e) {
                System.out.println("====> data:" + s);
            }
        });

        spark.stop();
    }
}

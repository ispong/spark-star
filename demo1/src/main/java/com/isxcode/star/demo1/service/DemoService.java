package com.isxcode.star.demo1.service;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Service;

@Service
public class DemoService {

    private final SparkSession sparkSession;

    public DemoService(SparkSession sparkSession) {
        this.sparkSession = sparkSession;
    }

    public void executeSql(String sql) {

        Dataset<Row> dataset = sparkSession.sql(sql);
        dataset.show();
    }
}

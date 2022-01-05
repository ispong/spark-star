package com.isxcode.star.demo1.controller;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class DemoController {

    private final SparkSession sparkSession;

    public DemoController(SparkSession sparkSession) {
        this.sparkSession = sparkSession;
    }

    @GetMapping("/demo")
    public String demo() {

        String sql = "select * from rd_dev.ispong_table limit 10";
        Dataset<Row> dataset = sparkSession.sql(sql);
        dataset.show();

        return "hello world";
    }
}

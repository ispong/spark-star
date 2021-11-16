package com.isxcode.demo1;

import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
@SpringBootApplication
public class DemoApplication {

    private final SparkSession sparkSession;

    public DemoApplication(SparkSession sparkSession) {
        this.sparkSession = sparkSession;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping("/demo")
    public String demo() {

        // 读取hive中的数据
        Dataset<Row> rowDataset = sparkSession.sql("select * from rd_dev.houseinfo");
        // 拦截过滤数据
        Dataset<Row> result = rowDataset.filter((FilterFunction<Row>) e -> "180".equals(String.valueOf(e.get(0))));
        // 将结果写入hive中一张新的表
        result.select("id", "theme")
                .write()
                .option("timestampFormat", "yyyy/MM/dd HH:mm:ss ZZ")
                .format("Hive")
                .mode(SaveMode.Overwrite)
                .saveAsTable("rd_dev.houseinfo_result");

        return "hello";
    }

}

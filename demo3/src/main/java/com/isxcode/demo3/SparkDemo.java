package com.isxcode.demo3;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class SparkDemo {

    public static void main(String[] args) {
        String appName = "ispong-demo";
        String master = "local";

        SparkConf conf = new SparkConf()
                .setAppName(appName)
                .setMaster(master);
        JavaSparkContext sc = new JavaSparkContext(conf);

        // 获取数据
        SparkSession sparkSession = SparkSession
                .builder()
                .appName(appName)
                .master(master)
                .config("hive.metastore.uris", "thrift://172.23.39.206:30123")
                .enableHiveSupport()
                .getOrCreate();
        Dataset<Row> rowDataset = sparkSession.sql("select * from rd_dev.ispong_table");
        JavaRDD<Row> distData = sc.parallelize(rowDataset.collectAsList());

        // 计算
        JavaRDD<Row> result = distData.filter((Function<Row, Boolean>) e -> "zhangsan".equals(String.valueOf(e.get(0))));

        // 打印结果
        result.foreach((VoidFunction<Row>) System.out::println);
    }

}

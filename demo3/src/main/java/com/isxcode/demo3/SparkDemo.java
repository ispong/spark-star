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
        System.out.println("1");
        sparkSession.sql("use rd_dev");
        System.out.println("5");
        Dataset<Row> rowDataset = sparkSession.sql("select * from ispong_table");
        System.out.println("2");
        JavaRDD<Row> distData = sc.parallelize(rowDataset.collectAsList());

        // 计算
        System.out.println("3");
        JavaRDD<Row> result = distData.filter((Function<Row, Boolean>) e -> "zhangsan".equals(String.valueOf(e.get(0))));

        // 打印结果
        System.out.println("4");
        result.foreach((VoidFunction<Row>) System.out::println);
    }

}

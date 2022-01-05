package com.isxcode.demo3;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class SparkDemo {

    public static void main(String[] args) {

        // 设置master类型
        String master = "local";
        // 创建sparkSession
        SparkSession sparkSession = SparkSession
                .builder()
                .appName("ispong-hive-demo")
                .master(master)
                .config("hive.metastore.uris", "thrift://172.23.39.206:30123")
                .config("spark.sql.hive.metastore.version", "2.1.1")
                .config("spark.sql.hive.metastore.jars", "/data/cdh/cloudera/parcels/CDH/lib/hive/lib/*")
                .enableHiveSupport()
                .getOrCreate();
        // 读取hive中的数据
        Dataset<Row> rowDataset = sparkSession.sql("select * from rd_dev.houseinfo");
        // 转为JavaSparkContext
        JavaSparkContext sc = JavaSparkContext.fromSparkContext(sparkSession.sparkContext());
        // 状态数据准备处理
        System.out.println("1");
        JavaRDD<Row> distData = sc.parallelize(rowDataset.collectAsList());
        System.out.println("2");
        // 开始
//        JavaRDD<Row> result = distData.filter((Function<Row, Boolean>) e -> "180".equals(String.valueOf(e.get(0))));

        // 打印结果
//        result.foreach((VoidFunction<Row>) e-> System.out.println(e.get(1)));
        System.out.println("3");

    }

}

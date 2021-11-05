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

        String master = "local";

        // 获取数据
        SparkSession sparkSession = SparkSession
                .builder()
                .appName("ispong-hive-demo")
                .master(master)
                .config("hive.metastore.uris", "thrift://172.23.39.206:30123")
                .config("spark.sql.hive.metastore.version", "2.1.1")
                .config("spark.sql.hive.metastore.jars", "/data/cdh/cloudera/parcels/CDH/lib/hive/lib/*")
                .enableHiveSupport()
                .getOrCreate();
        System.out.println("1");
        Dataset<Row> rowDataset = sparkSession.sql("select * from rd_dev.ispong_table");
        System.out.println("2");
        rowDataset.show();
        sparkSession.close();

        System.out.println("3");
        SparkConf conf = new SparkConf()
            .setAppName("ispong-demo")
            .setMaster(master);
        System.out.println("4");
        JavaSparkContext sc = new JavaSparkContext(conf);
        System.out.println("5");
        JavaRDD<Row> distData = sc.parallelize(rowDataset.collectAsList());


//        // 计算
//        System.out.println("3");
//        JavaRDD<Row> result = distData.filter((Function<Row, Boolean>) e -> "zhangsan".equals(String.valueOf(e.get(0))));
//
//        // 打印结果
//        System.out.println("4");
//        result.foreach((VoidFunction<Row>) System.out::println);
    }

}

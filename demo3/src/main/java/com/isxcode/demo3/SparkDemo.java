package com.isxcode.demo3;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SparkDemo {

    public static void main(String[] args) {
        String appName = "ispong-demo";
        String master = "local";

        SparkConf conf = new SparkConf()
                .setAppName(appName)
                .setMaster(master);
        JavaSparkContext sc = new JavaSparkContext(conf);

        // 获取数据

        List<Integer> data = Arrays.asList(1, 2, 3, 4, 5);
        JavaRDD<Integer> distData = sc.parallelize(data);

        List<Integer> result = new ArrayList<>();
        distData.foreach(integer -> result.add(integer + 1));
        System.out.println("=====> result");
        result.forEach(System.out::println);
    }

}

package com.isxcode.demo3;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;
import java.util.List;

public class SparkDemo {

    public static void main(String[] args) {
        String appName = "ispong-demo";
        String master = "local";

        SparkConf conf = new SparkConf().setAppName(appName).setMaster(master);

        JavaSparkContext sc = new JavaSparkContext(conf);

        List<Integer> data = Arrays.asList(1, 2, 3, 4, 5);
        JavaRDD<Integer> distData = sc.parallelize(data);

        System.out.println(distData.toString());
    }

}

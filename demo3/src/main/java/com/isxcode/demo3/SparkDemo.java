package com.isxcode.demo3;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;

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

        // 计算
        JavaRDD<Integer> result = distData.filter((Function<Integer, Boolean>) integer -> integer < 3);

        // 打印结果
        result.foreach((VoidFunction<Integer>) integer -> System.err.println("filter算子:" + integer));
    }

}

package com.isxcode.star.demo2;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class Demo {

    public static void main(String[] args) {

        // 构建新的sparkSession
        SparkSession sparkSession = SparkSession.builder()
            .master("yarn")
            .appName("demo2")
            .config("deploy-mode", "cluster")
            .config("spark.yarn.queue", "default")
            .config("hive.metastore.uris", "thrift://localhost:9083")
            .config("spark.driver.memory", "2g") // driver进程的内存 不用给太多
            .config("spark.driver.cores", 1) // driver进程的CPU core 数量
            // 1g 500m Size must be specified as bytes (b), kibibytes (k), mebibytes (m), gibibytes (g), tebibytes (t), or pebibytes(p). E.g. 50b, 100k, or 250m.
            // executor最大可申请内存,受限于yarn的总内存 (yarn-site.xml yarn.nodemanager.resource.memory-mb) 不可占满yarn的内存
            // 实际使用大概50%左右
            .config("spark.executor.memory", "2g")
            // 每一个executor使用cpu0-vcore, 受限于yarn的vcores (yarn.nodemanager.resource.cpu-vcores)
            .config("spark.executor.cores", 1)
            // 经过产看官方文档发现 --num-executors该属性只有在YARN模式下有效  可能申请不到资源
            .config("spark.executor.instances", 4)
//            动态分配
//            .config("spark.dynamicAllocation.enabled", true)
//            .config("spark.dynamicAllocation.initialExecutors", 3)
//            .config("spark.dynamicAllocation.minExecutors", 1)
//            .config("spark.shuffle.service.enabled", true)
            .config("spark.yarn.historyServer.allowTracking", true) // 允许historyServer监控
            .config("spark.sql.storeAssignmentPolicy", "LEGACY")
            .enableHiveSupport()
            .getOrCreate();

        // 执行spark
        System.out.println("开始执行");
        Dataset<Row> sql = sparkSession.sql("select count(1) from default.userinfo where username like '平%' and school in ('高中','初中')");
        System.out.println("开始结束");
        sql.show();

        // 停止session
        sparkSession.stop();
    }
}

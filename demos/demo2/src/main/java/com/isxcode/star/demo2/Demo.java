package com.isxcode.star.demo2;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;


public class Demo {

    /**
     *  bytes (b), kibibytes (k), mebibytes (m), gibibytes (g), tebibytes (t), or pebibytes(p). E.g. 50b, 100k, or 250m.
     */
    public static void main(String[] args) {

        // 构建新的sparkSession
        SparkSession sparkSession = SparkSession.builder()
            .master("yarn")
            .appName("demo2")
            .config("deploy-mode", "cluster")
            .config("spark.yarn.queue", "default")
            .config("hive.metastore.uris", "thrift://localhost:9083")
            .config("spark.driver.memory", "2g") // driver进程的内存，除非数据量特别大，否则不用给太多
            .config("spark.driver.cores", 1) // driver进程的cpu-vcore个数，除非数据量特别大，否则不用给太多
            // 单个executor最大可申请内存,受限于yarn的总内存 (yarn-site.xml文件中的yarn.nodemanager.resource.memory-mb配置，需要重启yarn) 不可占满yarn的内存
            // 实际申请不会打满，一般设置2g，会申请1.5个g
            .config("spark.executor.memory", "2g")
            // 单个executor使用cpu-vcore的个数, 受限于yarn的vcores总个数 (yarn-site.xml文件中的yarn.nodemanager.resource.cpu-vcores配置，需要重启yarn) 不可占满yarn的vcore
            // 一个core处理，可处理100M+的数据量，设置太多会浪费
            .config("spark.executor.cores", 1)
            // 一个作业需要使用executor个数，看情况设置，设置太多的话，会浪费资源，设置太少的话，运行会慢。
            // 如果资源不足的话，设置4，可现资源只够3个使用，它会等待一会儿资源释放，这边会变慢，如果资源还是不足的话，它会申请2个，然后继续执行
            // 如果资源一个都不够的话，spark作业直接卡死
            .config("spark.executor.instances", 2)
//            高级玩法-动态分配，暂时不需要，上面的配置调一调，应该就快起来了
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
        Dataset<Row> sql = sparkSession.sql("select count(1) from default.userinfo_big where username like '松%' and school in ('高中','初中')");
        System.out.println("开始结束");
        sql.show();

        // 停止session
        sparkSession.stop();
    }
}

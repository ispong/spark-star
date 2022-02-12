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
            .config("spark.driver.memory", "2g") // 设置driver进程的内存，除非数据量特别大，否则不用给太多
            .config("spark.driver.cores", 1) // 设置driver进程的cpu-core个数，除非数据量特别大，否则不用给太多
            // 单个executor最大可申请使用内存,受限于yarn的总内存 (yarn-site.xml文件中的yarn.nodemanager.resource.memory-mb配置，修改后需要重启yarn) 不可占满yarn的总内存
            // 实际申请不会打满，一般设置2g，会申请1.3个g左右
            .config("spark.executor.memory", "1g")
            // 单个executor使用cpu-core的个数, 受限于yarn的cores总个数 (yarn-site.xml文件中的yarn.nodemanager.resource.cpu-vcores配置，修改后需要重启yarn) 不可占满yarn的vcore总个数
            // 一个core处理，可处理100M+的数据量，设置太多会浪费
            .config("spark.executor.cores", 1)
            // 每个作业需要使用executor的个数，看情况设置，设置太多的话，会浪费资源，设置太少的话，运行会慢。设置太多申请资源，也会变慢，且浪费资源。
            // 如果资源不足的话，设置为4，可现有资源只够3个使用，它会等待资源释放，时间会变慢，如果资源还是不足，它会申请2个，然后执行spark作业。
            // 如果等待后资源一个都不够的话，spark作业直接卡死失败。
            .config("spark.executor.instances", 6)
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
        Dataset<Row> sql = sparkSession.sql("select count(1) from default.userinfo_big where username like '平%' and school in ('高中','初中')");
        System.out.println("开始结束");
        sql.show();

        // 停止session
        sparkSession.stop();
    }
}

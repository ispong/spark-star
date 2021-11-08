package com.isxcode.demo1;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Stream;

@RequestMapping
@RestController
@SpringBootApplication
public class DemoApplication {

	private final SparkSession sparkSession;

	public DemoApplication(SparkSession sparkSession) {
		this.sparkSession = sparkSession;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/demo")
	public String demo() {

		// 设置master类型
		String master = "yarn";
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
		//数据库内容
		String url = "jdbc:hive2://39.103.230.188:30115/RD_DEV";
		Properties connectionProperties = new Properties();
		connectionProperties.put("user", "dehoop");
//		connectionProperties.put("driver", "org.apache.hive.jdbc.HiveDriver");
		//将数据通过覆盖的形式保存在数据表中
		rowDataset.write().mode(SaveMode.Overwrite).jdbc(url, "houseinfo_result", connectionProperties);

//		Dataset<Row> rowDataset = sparkSession.sql("select * from rd_dev.houseinfo");
		// 转为JavaSparkContext
//		JavaSparkContext sc = JavaSparkContext.fromSparkContext(sparkSession.sparkContext());
		// 状态数据准备处理
//		JavaRDD<Row> distData = sc.parallelize(rowDataset.collectAsList());
		// 开始
//		JavaRDD<Row> result = distData.filter((Function<Row, Boolean>) e -> "180".equals(String.valueOf(e.get(0))));
		// 打印结果
//		result.foreach((VoidFunction<Row>) e-> System.out.println(e.get(1)));

//		distData.filter((Function<Row, Boolean>) e -> "180".equals(String.valueOf(e.get(0))));
//		distData.filter((Function<Row, Boolean>) e -> "180".equals(String.valueOf(e.get(0))));

		return "hello";
	}

}

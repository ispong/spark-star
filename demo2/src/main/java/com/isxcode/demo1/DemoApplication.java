package com.isxcode.demo1;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FilterFunction;
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
		// 转为JavaSparkContext
//		JavaSparkContext sc = JavaSparkContext.fromSparkContext(sparkSession.sparkContext());
		// 状态数据准备处理
//		JavaRDD<Row> distData = sc.parallelize(rowDataset.collectAsList());
		// 开始
		Dataset<Row> result = rowDataset.filter((FilterFunction<Row>)  e -> "180".equals(String.valueOf(e.get(0))));
		//数据库内容
//		Dataset<Row> dataFrame = sparkSession.createDataFrame(result, Object.class);
		result.select("id", "theme")
				.write()
				.option("timestampFormat", "yyyy/MM/dd HH:mm:ss ZZ")
				.format("Hive")
				.mode(SaveMode.Overwrite)
				.saveAsTable("rd_dev.houseinfo_result");

		return "hello";
	}

}

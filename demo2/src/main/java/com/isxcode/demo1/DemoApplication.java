package com.isxcode.demo1;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
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

		String colName = "username";
		String sql = "select * from rd_dev.ispong_table";
		Dataset<Row> dataset = sparkSession.sql(sql);
//		dataset.show();
		List<String> columns = Arrays.asList(dataset.columns());
		List<Row> rows = dataset.collectAsList();
		System.out.println(rows.get(0).get(columns.indexOf(colName)));;

		return "hello world";
	}

}

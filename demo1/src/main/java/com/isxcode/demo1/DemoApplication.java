package com.isxcode.demo1;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
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

		String sql = "select count(1) from default.demo";
		Dataset<Row> dataset = sparkSession.sql(sql);
		dataset.show();

		return "hello world";
	}

}

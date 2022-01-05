package com.isxcode.star.demo1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@Slf4j
@RequestMapping
@RestController
@SpringBootApplication
public class Demo1Application {

	public static void main(String[] args) {

		SpringApplication.run(Demo1Application.class, args);
	}

	@Bean
	public CommandLineRunner run(ApplicationContext appContext) {

		System.getenv().forEach((k, v) -> System.out.println(k + "---" + v));

		return args -> Arrays.stream(appContext.getBeanDefinitionNames()).sorted().forEach(log::debug);
	}

}

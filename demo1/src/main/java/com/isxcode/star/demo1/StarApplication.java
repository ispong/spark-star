package com.isxcode.star.demo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
@SpringBootApplication
public class StarApplication {

	public static void main(String[] args) {

		SpringApplication.run(StarApplication.class, args);
	}
}

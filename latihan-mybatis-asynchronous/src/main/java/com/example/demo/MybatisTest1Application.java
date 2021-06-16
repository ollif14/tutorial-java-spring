package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.demo.mybatisTest1.mapper")
@SpringBootApplication
public class MybatisTest1Application {
	

	public static void main(String[] args) {
		SpringApplication.run(MybatisTest1Application.class, args);

	}

}

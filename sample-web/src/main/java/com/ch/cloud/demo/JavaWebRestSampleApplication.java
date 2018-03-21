package com.ch.cloud.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.ch.cloud.demo")
@MapperScan("com.ch.cloud.demo.dao")
public class JavaWebRestSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaWebRestSampleApplication.class, args);
	}
}

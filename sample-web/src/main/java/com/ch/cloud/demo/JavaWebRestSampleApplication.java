package com.ch.cloud.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.ch.cloud.demo")
@MapperScan("com.ch.cloud.demo.dao")
@Configuration
@EnableFeignClients
public class JavaWebRestSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaWebRestSampleApplication.class, args);
	}
}

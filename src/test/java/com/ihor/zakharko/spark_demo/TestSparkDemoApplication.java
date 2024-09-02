package com.ihor.zakharko.spark_demo;

import org.springframework.boot.SpringApplication;

public class TestSparkDemoApplication {

	public static void main(String[] args) {
		SpringApplication.from(SparkDemoApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}

package com.infyniteloop.ba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages="com.infyniteloop.ba")
public class BaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaApplication.class, args);
	}

}

package com.demo.gk0624;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.demo.*")
public class Gk0624Application {

	public static void main(String[] args) {
		SpringApplication.run(Gk0624Application.class, args);
	}

}

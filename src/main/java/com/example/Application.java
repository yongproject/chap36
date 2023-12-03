package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		System.out.println("###################");
		System.out.println("# Spring Boot START");
		System.out.println("###################");
		SpringApplication.run(Application.class, args);
		
		System.out.println("###################");
		System.out.println("# Spring Boot END");
		System.out.println("###################");
	}

}

package com.example.cars_info;

import com.example.cars_info.service.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CarsInfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarsInfoApplication.class, args);
		System.out.println("Hi");
	}

}

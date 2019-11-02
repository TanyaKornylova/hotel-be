package com.netcracker.hotelbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com")
@SpringBootApplication
public class HotelBeApplication {
	public static void main(String[] args) {
		SpringApplication.run(HotelBeApplication.class, args);
	}
}

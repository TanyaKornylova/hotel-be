package com.netcracker.hotelbe;

import com.netcracker.hotelbe.resources.Hello;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class HotelBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelBeApplication.class, args);
	}

}

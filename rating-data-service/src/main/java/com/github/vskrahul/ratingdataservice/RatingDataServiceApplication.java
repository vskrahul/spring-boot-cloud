package com.github.vskrahul.ratingdataservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableCircuitBreaker
//@EnableHystrixDashboard
public class RatingDataServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RatingDataServiceApplication.class, args);
	}

}

package com.example.bsm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BloodSampleManagementApiApplication {

	public static void main(String[] args) {

		SpringApplication.run(BloodSampleManagementApiApplication.class, args);

	}
}

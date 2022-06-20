package com.db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
//@EntityScan(basePackages = "com.db.entity")
public class AdminApiAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminApiAppApplication.class, args);
	}

}

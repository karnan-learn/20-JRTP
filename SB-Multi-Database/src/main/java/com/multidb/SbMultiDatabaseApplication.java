package com.multidb;

import java.util.HashMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@SpringBootApplication
public class SbMultiDatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbMultiDatabaseApplication.class, args);
	}
	

}

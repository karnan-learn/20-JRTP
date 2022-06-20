package com.karnan.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringRestJmeterTestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestJmeterTestApiApplication.class, args);
	}
	@GetMapping("/welcome")
	public String welcome() {
		return "welcome";
	}
}

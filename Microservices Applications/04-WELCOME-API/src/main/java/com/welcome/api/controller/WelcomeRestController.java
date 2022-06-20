package com.welcome.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.welcome.api.restcalls.GreetApiClient;


@RestController
public class WelcomeRestController {
	
	private Logger logger = LoggerFactory.getLogger(WelcomeRestController.class);
	
	@Autowired
	private GreetApiClient greetClient;

	@GetMapping("/welcome/{name}")
	public String welcomeMsg(@PathVariable("name") String name) {

		logger.info("welcomeMsg() execution - start");

		String welcomeMsg = "Welcome to Ashok IT..!!";

		String greetMsg = greetClient.invokeGreetApi(name);

		logger.info("welcomeMsg() execution - end ");

		return greetMsg + ", " + welcomeMsg;
	}
}

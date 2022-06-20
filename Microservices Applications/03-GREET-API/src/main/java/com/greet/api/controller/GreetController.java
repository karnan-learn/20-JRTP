package com.greet.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GreetController {

	private Logger logger = LoggerFactory.getLogger(GreetController.class);
	
	@Autowired
	private Environment env;
	
	@GetMapping("/greet/{name}")
	public String Greetings(@PathVariable("name") String name) {
		logger.info("Greetings() execution - start");
		String port = env.getProperty("server.port");
		
		String msg = name + "Good morning...!!(Server Port :: "+port+")";
		logger.info("Greetings() execution - end");
		return msg;
	}
}

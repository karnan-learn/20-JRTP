package com.welcome.api.restcalls;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "GREET-API")
public interface GreetApiClient {
	
	@GetMapping("/greet/{name}")
	public String invokeGreetApi(@PathVariable("name") String name);
}

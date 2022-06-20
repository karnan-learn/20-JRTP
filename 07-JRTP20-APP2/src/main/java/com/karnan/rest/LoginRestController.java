package com.karnan.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karnan.bindings.LoginForm;
import com.karnan.service.UserMgmtServiceImpl;

@RestController
@RequestMapping("/")
public class LoginRestController {
	
	@Autowired
	private UserMgmtServiceImpl userMgmtServiceImpl;
	
	@PostMapping("signin")
	public ResponseEntity<String> signin(@RequestBody LoginForm loginForm){
		String status = userMgmtServiceImpl.signIn(loginForm);
		return new ResponseEntity<>(status,HttpStatus.OK);
	}
}

package com.karnan.rest;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karnan.service.UserMgmtServiceImpl;

@RestController
@RequestMapping("/")
public class ForgotPwdRestController {

	@Autowired
	private UserMgmtServiceImpl UserMgmtServiceImpl;
	
	@GetMapping("forget-password")
	public ResponseEntity<String> forgetPassword(@PathParam(value = "email") String email){
		String status = UserMgmtServiceImpl.forgetPassword(email);
		return new ResponseEntity<String>(status,HttpStatus.OK);
	}
}

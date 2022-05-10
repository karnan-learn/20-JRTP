package com.karnan.rest;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karnan.bindings.UserRegForm;
import com.karnan.service.UserMgmtServiceImpl;

@RestController
@RequestMapping("/")
public class RegistrationRestController {

	@Autowired
	private UserMgmtServiceImpl UserMgmtServiceImpl;
	
	@PostMapping("signup")
	public ResponseEntity<String> signUp(@RequestBody UserRegForm userRegForm) {
		String status = UserMgmtServiceImpl.signUp(userRegForm);
		return new ResponseEntity<String>(status,HttpStatus.CREATED);
	}
	@GetMapping("email-available-check")
	public ResponseEntity<String> emailAvailableCheck(@PathParam(value = "email") String email){
		if(UserMgmtServiceImpl.isEmailExistsOrNot(email)) {
			return new ResponseEntity<String>("Email is already available",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Email is valid",HttpStatus.OK);
		}
	}
	@GetMapping("countries")
	public ResponseEntity<String> getAllContries(){
		return null;
	}
	@GetMapping("states/{countryId}")
	public ResponseEntity<String> getStatesByCountryId(@PathVariable(value = "conutryId" ) int countryId){
		return null;
	}
}

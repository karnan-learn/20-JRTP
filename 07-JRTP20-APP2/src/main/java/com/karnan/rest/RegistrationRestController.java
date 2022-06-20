package com.karnan.rest;

import java.util.Map;

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
	private UserMgmtServiceImpl userMgmtServiceImpl;
	
	@PostMapping("signup")
	public ResponseEntity<String> signUp(@RequestBody UserRegForm userRegForm) {
		String status = userMgmtServiceImpl.signUp(userRegForm);
		return new ResponseEntity<>(status,HttpStatus.CREATED);
	}
	
	@GetMapping("email-available-check")
	public ResponseEntity<String> emailAvailableCheck(@PathParam(value = "email") String email){
		if(userMgmtServiceImpl.isEmailExistsOrNot(email)) {
			return new ResponseEntity<>("Email is already available",HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Email is valid",HttpStatus.OK);
		}
	}
	
	@GetMapping("countries")
	public ResponseEntity<Map<Integer, String>> getAllContries(){
		return new ResponseEntity<>(userMgmtServiceImpl.getAllContries(),HttpStatus.OK);
	}
	
	@GetMapping("state/{countryId}")
	public ResponseEntity<Map<Integer, String>> getStatesByCountryId(@PathVariable(value = "countryId") int countryId){
		return new ResponseEntity<>(userMgmtServiceImpl.getAllStatesByCountryId(countryId) ,HttpStatus.OK);
	}
	
	@GetMapping("cities/{stateId}")
	public ResponseEntity<Map<Integer, String>> getCityByStateId(@PathVariable(value = "stateId") int stateId){
		return new ResponseEntity<>(userMgmtServiceImpl.getAllCitiesByStateId(stateId) ,HttpStatus.OK);
	}
	
}

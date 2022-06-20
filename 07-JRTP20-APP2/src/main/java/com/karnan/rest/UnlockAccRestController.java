package com.karnan.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karnan.bindings.UnlockAccForm;
import com.karnan.service.UserMgmtServiceImpl;

@RestController
@RequestMapping("/")
public class UnlockAccRestController {

	@Autowired
	private UserMgmtServiceImpl userMgmtServiceImpl;
	
	@PostMapping("account-reset")
	public ResponseEntity<String> accUlock(@RequestBody UnlockAccForm unlockAccForm){
		String status = userMgmtServiceImpl.accUlock(unlockAccForm);
		return new ResponseEntity<>(status,HttpStatus.OK);
	}
}

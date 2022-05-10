package com.karnan.service;

import java.util.HashMap;
import java.util.Map;

import com.karnan.bindings.LoginForm;
import com.karnan.bindings.UnlockAccForm;
import com.karnan.bindings.UserRegForm;

public interface UserMgmtService {
	//Sign
	String signIn(LoginForm loginForm);

	//SignUp
	String signUp(UserRegForm user);
	boolean isEmailExistsOrNot(String email);
	Map<Integer,String> getAllContries();
	Map<Integer,String> getAllStatesByCountryId(int countryId);
	Map<Integer,String> getAllCitiesByStateId(int stateId);
	
	//Account Unlock
	String accUlock(UnlockAccForm unlockAccForm);
	
	//ForgetPassword
	String forgetPassword(String email);
	
	
}

package com.karnan.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.karnan.bindings.LoginForm;
import com.karnan.bindings.UnlockAccForm;
import com.karnan.bindings.UserRegForm;
import com.karnan.entity.CityMasterEntity;
import com.karnan.entity.CountryMasterEntity;
import com.karnan.entity.StateMasterEntity;
import com.karnan.entity.UserDtlsEntity;
import com.karnan.repository.CityMasterRepo;
import com.karnan.repository.CountryMasterRepo;
import com.karnan.repository.StateMasterRepo;
import com.karnan.repository.UserDtlsRepo;
import com.karnan.util.PasswordUtils;
import com.karnan.util.ReadMailBody;
import com.karnan.util.SendEmail;

@Service
public class UserMgmtServiceImpl implements UserMgmtService {

	@Autowired
	private UserDtlsRepo userDtlsRepo;
	@Autowired
	private SendEmail util;
	@Autowired
	private UserDtlsEntity userDtl;
	@Autowired
	private CountryMasterRepo countryMasterRepo;
	@Autowired
	private StateMasterRepo stateMasterRepo;
	@Autowired
	private CityMasterRepo cityMasterRepo;
	@Autowired
	private ReadMailBody readMailBody;
	
	@Value(value =  "${unlockAccountEmailTemplate}")
	String unlockAccountEmailTemplate;
	
	@Value(value =  "${recoveryPasswordEmailTemplate}")
	String recoveryPasswordEmailTemplate;
	@Override
	public String signUp(UserRegForm user) throws IllegalArgumentException{
		System.out.println(unlockAccountEmailTemplate+" "+recoveryPasswordEmailTemplate);
		BeanUtils.copyProperties(user, userDtl);
		userDtl.setUserPwd(PasswordUtils.generateRandomPassword(12));
		userDtl.setAccStatus(false);
		UserDtlsEntity savedUser = userDtlsRepo.save(userDtl); 
		if(savedUser!=null) {
			boolean sent = util.send(userDtl.getUserEmail(),
					new String[] {},
					new String[] {}, 
					"Unlock IES Account", 
					readMailBody.readMailBodyContent(unlockAccountEmailTemplate, savedUser), 
					new Resource[] {});
			System.out.println(sent);
			if(sent) {
				return "User Registration Successfull && Check Your Email for Temporary Password";
			}else {
				userDtlsRepo.deleteById(savedUser.getUserId());
				return "Sending Email Failed && again SignUp";
			}
		}else {
			return "User Registration Failed && Try again";
		}
	}

	@Override
	public boolean isEmailExistsOrNot(String email) {
		UserDtlsEntity userDtlsEntity = userDtlsRepo.findByUserEmail(email);
		System.out.println(userDtlsEntity);
		if(userDtlsEntity!=null) {
			if(userDtlsEntity.getUserEmail().equals(email)) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}

	@Override
	public Map<Integer, String> getAllContries() {
		List<CountryMasterEntity> countries = countryMasterRepo.findAll();
		Map<Integer,String> countryMap = new HashMap<>();
		for(CountryMasterEntity entity : countries) {
			countryMap.put(entity.getCountryID(), entity.getCountryName());
		}
		return countryMap;
	}

	@Override
	public Map<Integer, String> getAllStatesByCountryId(int countryId) {
		List<StateMasterEntity> states = stateMasterRepo.findByCountryId(countryId);
		Map<Integer,String> stateMap = new HashMap<>();
		for(StateMasterEntity entity : states) {
			stateMap.put(entity.getStateId(), entity.getStateName());
		}
		return stateMap;
	}

	@Override
	public Map<Integer, String> getAllCitiesByStateId(int stateId) {
		List<CityMasterEntity> cities = cityMasterRepo.findByStateID(stateId);
		Map<Integer,String> cityMap = new HashMap<>();
		for(CityMasterEntity entity : cities) {
			cityMap.put(entity.getCityId(), entity.getCityName());
		}
		return cityMap;
	}

	@Override
	public String signIn(LoginForm loginForm) {
		UserDtlsEntity userDtl = userDtlsRepo.findByUserEmail(loginForm.getEmail());
		if(userDtl.getUserEmail().equals(loginForm.getEmail())){
			if(userDtl.isAccStatus()) {
				return "Welcome to Ashok IT….";
			}else {
				return "Your Account Is Locked";
			}
		}else {
			return "Invalid Credentials";
		}
	}

	@Override
	public String forgetPassword(String email) {
		System.out.println(email);
		try {
			UserDtlsEntity user = userDtlsRepo.findByUserEmail(email);
			System.out.println(user);
			if(user.isAccStatus()) {
				if(isEmailExistsOrNot(email) && 
						 util.send(user.getUserEmail(),
								new String[] {},
								new String[] {}, 
								"Unlock IES Account", 
								readMailBody.readMailBodyContent(recoveryPasswordEmailTemplate, user), 
								new Resource[] {})
						) {
					return "Password mailed";
				}else {
					return "Email is wrong || Sending email is failed";
				}
			}else {
				return "Please Unlock your accunt first";
			}
		}catch(NullPointerException e) {
			e.printStackTrace();
		}
		return "Email does not exists";
	}

	@Override
	public String accUlock(UnlockAccForm unlockAccForm) {
		if(( unlockAccForm.getNewPwd().equals(unlockAccForm.getConfirmNewPwd()) ) &&  ( unlockAccForm.getTempPwd().equals(userDtlsRepo.findByUserEmail(unlockAccForm.getEmail()).getUserPwd()) )) {
			UserDtlsEntity userDtlsEntity = userDtlsRepo.findByUserEmail(unlockAccForm.getEmail());
			userDtlsEntity.setUserPwd(unlockAccForm.getNewPwd());
			userDtlsEntity.setAccStatus(true);
			userDtlsRepo.save(userDtlsEntity); 
			return "Account unlocked, please proceed with login";
		}else {
			return "Password doesn't match or Your temp passwprd wrong";
		}
	}

}

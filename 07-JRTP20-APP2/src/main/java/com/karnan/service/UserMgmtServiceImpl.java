package com.karnan.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Override
	public String signUp(UserRegForm user) throws IllegalArgumentException{
		userDtl.setFirstName(user.getFirstName());
		userDtl.setLastName(user.getLastName());
		userDtl.setUserEmail(user.getUserEmail());
		userDtl.setUserMobile(user.getUserMobile());
		userDtl.setDob(user.getDob());
		userDtl.setGender(user.getGender());
		userDtl.setCountryId(user.getCountryId());
		userDtl.setStateId(user.getStateId());
		userDtl.setCityId(user.getCityId());
		userDtl.setUserPwd(PasswordUtils.generateRandomPassword(12));
		userDtl.setAccStatus(false);
		UserDtlsEntity savedUser = userDtlsRepo.save(userDtl);
		if(savedUser!=null) {
			String htmlmsg = "<html>\r\n"
					+ "  <body>\r\n"
					+ "    <h3>Hi "+userDtl.getFirstName() +" "+ userDtl.getLastName() +" :</h3>\r\n"
					+ "    <p>Welcome to IES family, your registration is almost complete.</p>\r\n"
					+ "    <p>Please unlock your account below details</p>\r\n"
					+ "    <p>Temporary Password : "+ userDtl.getUserPwd()+"</p>\r\n"
					+ "    <p><a href='www.google.com'>Link to unlock account</a></p>\r\n"
					+ "    <h4>Thanks,</h4>\r\n"
					+ "    <h4>IES Team.</h4>\r\n"
					+ "  </body>\r\n"
					+ "</html>";
			boolean sent = util.send(userDtl.getUserEmail(),
					new String[] {},
					new String[] {}, 
					"Unlock IES Account", 
					htmlmsg, 
					new Resource[] {});
			System.out.println(sent);
			if(sent) {
				return "User Registration Successfull && Check Your Email for Temporary Password";
			}else {
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
		System.out.println(states);
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
			String htmlmsg = "<html>\r\n"
					+ "  <body>\r\n"
					+ "    <h3>Hi "+user.getFirstName() +" "+ user.getLastName() +" :</h3>\r\n"
					+ "    <p>Welcome to IES family, your password is recovered.</p>\r\n"
					+ "    <p>Please login your account using below password</p>\r\n"
					+ "    <p>Your Password : "+ user.getUserPwd()+"</p>\r\n"
					+ "    <p><a href='www.google.com'>Login to account</a></p>\r\n"
					+ "    <h4>Thanks,</h4>\r\n"
					+ "    <h4>IES Team.</h4>\r\n"
					+ "  </body>\r\n"
					+ "</html>";
			if(user.isAccStatus()) {
				if(isEmailExistsOrNot(email) && 
						 util.send(user.getUserEmail(),
								new String[] {},
								new String[] {}, 
								"Unlock IES Account", 
								htmlmsg, 
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

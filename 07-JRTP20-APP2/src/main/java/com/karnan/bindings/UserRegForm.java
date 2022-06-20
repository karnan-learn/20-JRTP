package com.karnan.bindings;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserRegForm {

	private Integer userId;
	private String firstName;
	private String lastName;
	private String userEmail;
	private String userPwd;
	private String userMobile;
	private LocalDate dob;
	private String gender;
	private Integer cityId;
	private Integer stateId;
	private Integer countryId;
	private LocalDate createdDate;
	private LocalDate updatedDate;
}

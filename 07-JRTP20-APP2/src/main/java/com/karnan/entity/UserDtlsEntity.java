package com.karnan.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import lombok.Data;
@Entity
@Table(name="USER_DTLS")
@Data
@Component
public class UserDtlsEntity {
	@Id
	@GeneratedValue
	@Column(name="USER_ID")
	private Integer userId;
	
	@Column(name="firstName")
	private String firstName;
	
	@Column(name="LAST_NAME")
	private String lastName;
	
	@Column(name="USER_EMAIL", unique=true)
	private String userEmail;
	
	@Column(name="USER_PWD")
	private String userPwd;
	
	@Column(name="USER_MOBILE")
	private String userMobile;
	
	@Column(name="DOB")
	private LocalDate dob;
	
	@Column(name="GENDER")
	private String gender;
	
	@Column(name="CITY_ID")
	private Integer cityId;
	
	@Column(name="STATE_ID")
	private Integer stateId;
	
	@Column(name="COUNTRY_ID")
	private Integer countryId;
	
	@Column(name="ACC_STATUS")
	private boolean accStatus;
	
	@Column(name="CREATED_DATE", updatable = false)
	@CreationTimestamp
	private LocalDate createdDate;
	
	@Column(name="UPDATED_DATE", insertable = false)
	@UpdateTimestamp
	private LocalDate updatedDate;
	

}

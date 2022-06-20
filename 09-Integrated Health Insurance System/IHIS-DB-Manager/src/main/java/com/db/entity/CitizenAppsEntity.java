package com.db.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "CITIZEN_APPS")
@NoArgsConstructor
@AllArgsConstructor
public class CitizenAppsEntity {

	@Id
	@GeneratedValue
	@Column(name="CASE_NUM")
	private Integer caseNum;
	
	@Column(name="FULLNAME")
	private String fullName;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="PWD")
	private String pwd;
	
	@Column(name="MOBILE_NUM")
	private Integer mobileNum;
	
	@Column(name="GENDER")
	private String gender;
	
	@Column(name="DOB")
	private LocalDate dob;
	
	@Column(unique=true,name="SSN")
	private Integer ssn;
	
	@Column(name="STATE_NAME")
	private Integer stateName;
	
	@Column(name="ACTIVE_SW")
	private Character activeSw;
	
	@Column(name="CREATED_DATE")
	private LocalDate createdDate;
	
	@Column(name="UPDATED_DATE")
	private LocalDate updatedDate;
	
	@Column(name="CREATED_BY")
	private String createdBy;
	
	@Column(name="UPDATED_BY")
	private String updatedBy;
}

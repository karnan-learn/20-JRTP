package com.multidb.db1.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER_TB")
public class User {

	@Id
	@GeneratedValue
	private int id;
	private String userName;
	private String email;
//	private Long phno;
}

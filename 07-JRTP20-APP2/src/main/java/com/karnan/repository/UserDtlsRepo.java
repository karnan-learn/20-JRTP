package com.karnan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karnan.bindings.UserRegForm;
import com.karnan.entity.UserDtlsEntity;

public interface UserDtlsRepo extends JpaRepository<UserDtlsEntity, Integer> {

	UserDtlsEntity findByUserEmail(String userEmail);

}

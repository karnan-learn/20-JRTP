package com.karnan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karnan.entity.StateMasterEntity;

public interface StateMasterRepo extends JpaRepository<StateMasterEntity,Integer> {

	List<StateMasterEntity> findByCountryId(Integer countryId);
}

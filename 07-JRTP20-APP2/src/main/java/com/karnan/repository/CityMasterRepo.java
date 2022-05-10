package com.karnan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karnan.entity.CityMasterEntity;

public interface CityMasterRepo extends JpaRepository<CityMasterEntity,Integer> {

	List<CityMasterEntity> findByStateID(Integer stateID);
}

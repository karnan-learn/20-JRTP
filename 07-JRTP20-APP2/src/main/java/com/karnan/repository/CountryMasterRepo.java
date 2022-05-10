package com.karnan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karnan.entity.CountryMasterEntity;

public interface CountryMasterRepo extends JpaRepository<CountryMasterEntity, Integer> {

}

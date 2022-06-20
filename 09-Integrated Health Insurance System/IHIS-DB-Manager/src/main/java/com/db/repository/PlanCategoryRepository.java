package com.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.db.entity.PlanCategoryEntity;
@Repository
public interface PlanCategoryRepository extends JpaRepository<PlanCategoryEntity, Integer>{

}

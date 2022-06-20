package com.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.db.entity.AppPlanEntity;
@Repository
public interface AppPlanRepository extends JpaRepository<AppPlanEntity, Integer>{

    @Query(value = "select * from AppPlanEntity plan where plan.activeSw= :active",nativeQuery = true)
    public List<AppPlanEntity> findAllByactivePlan(@Param("active") char active);

    @Query(value = "select * from AppPlanEntity plan where plan.planId= :planId and plan.activeSw= :active",nativeQuery = true)
    public AppPlanEntity findByPlanIdAndactivePlan(@Param("planId") int planId, @Param("active") char active);
}

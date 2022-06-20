package com.db.service;

import java.util.List;

import com.db.bindings.Plan;
import com.db.entity.AppPlanEntity;

public interface PlanEntityService {
	
	public String upsert(Plan plan);
	public List<AppPlanEntity> getAllPlans();
	public AppPlanEntity getPlan(int planId);
	public String deletePlan(int planId);
	public String PlanActiveOrDeactive(int planId);
	
}

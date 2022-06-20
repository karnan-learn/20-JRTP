package com.db.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.bindings.Plan;
import com.db.entity.AppPlanEntity;
import com.db.repository.AppPlanRepository;

@Service
public class PlanEntityServiceImpl implements PlanEntityService{

	@Autowired
	private AppPlanRepository repo;
	
	@Override
	public String upsert(Plan plan) {
		System.out.println(plan);
		AppPlanEntity appPlanEntity =  new AppPlanEntity();
		
		appPlanEntity.setPlanName(plan.getPlanName());
		
		appPlanEntity.setPlanStDate(plan.getPlanStartDate());
		
		appPlanEntity.setPlanEnDate(plan.getPlanEndDate());
		
		appPlanEntity.setCategoryId(plan.getCategoryId());
		
		appPlanEntity.setActiveSw('Y');
//		System.out.println(appPlanEntity);
		System.out.println(repo.save(appPlanEntity));
		repo.save(appPlanEntity);
		return "SUCCESS";
	}

	@Override
	public List<AppPlanEntity> getAllPlans() {
		List<AppPlanEntity> plans = repo.findAllByactivePlan('Y');
		System.out.println(plans);
		return plans;
	}

	@Override
	public AppPlanEntity getPlan(int planId) {
		AppPlanEntity plan = repo.findByPlanIdAndactivePlan(planId,'Y');
		if(plan != null) {
			return plan;
		}else {
			return null;
		}
	}

	@Override
	public String deletePlan(int planId) {
		repo.deleteById(planId);
		return "Deleted Sucess";
	}

	@Override
	public String PlanActiveOrDeactive(int planId) {
		
		Optional<AppPlanEntity> findById = repo.findById(planId);
		
		if (findById.isPresent()) {
			AppPlanEntity plan = findById.get();
			plan.setActiveSw('N');
			repo.save(plan);
		}
		return "Deleted Sucess";
	}

}

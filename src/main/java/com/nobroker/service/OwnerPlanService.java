package com.nobroker.service;

import com.nobroker.payload.OwnerPlanDto;

import java.util.List;

public interface OwnerPlanService {


    public  List<OwnerPlanDto> getAllOwnerPlans();   // get All owner plans


    public OwnerPlanDto createOwnerPlans(OwnerPlanDto ownerPlanDto); // Create Owner plans



}

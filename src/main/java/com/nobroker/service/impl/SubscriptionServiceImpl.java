package com.nobroker.service.impl;

import com.nobroker.entity.SubscriptionDetails;
import com.nobroker.repository.SubscriptionPlanRepository;
import com.nobroker.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private SubscriptionPlanRepository ownerPlanRepository2;
    @Override
    public SubscriptionDetails subscribeOwnerPlan(long userId, int duration) {  //subscribe owner plan

        LocalDate currentDate = LocalDate.now();
        LocalDate expirationDate = currentDate.plusDays(duration);

        SubscriptionDetails ownerPlan2 = new SubscriptionDetails();
        ownerPlan2.setUserId(userId);
        ownerPlan2.setSubscriptionActive(true);
        ownerPlan2.setSubscriptionActiveDate(currentDate);
        ownerPlan2.setSubscriptionExpirationDate(expirationDate);
        ownerPlan2.setNumberOfDays(duration);

        return ownerPlanRepository2.save(ownerPlan2);
    }

    @Override
    public SubscriptionDetails getOwnerPlan(long ownerPlanId) {          //get owner plan
      return  ownerPlanRepository2.findById(ownerPlanId).orElse(null);
    }
}

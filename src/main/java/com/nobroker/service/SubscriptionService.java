package com.nobroker.service;

import com.nobroker.entity.SubscriptionDetails;

public interface SubscriptionService {
   

    SubscriptionDetails subscribeOwnerPlan(long userId, int duration);

    SubscriptionDetails getOwnerPlan(long ownerPlanId);
}

package com.nobroker.controller;



import com.nobroker.entity.SubscriptionDetails;
import com.nobroker.service.SubscriptionService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/owner-plans")
public class SubscriptionController {

    @Autowired
    private SubscriptionService ownerPlanService2;

    //http://localhost:8080/api/owner-plans/subscribe/{userId}/{duration}
    @PostMapping("/subscribe/{userId}/{duration}")
    public SubscriptionDetails subscribeOwnerPlan(@PathVariable long userId, @PathVariable int duration) {
        SubscriptionDetails createdOwnerPlan = ownerPlanService2.subscribeOwnerPlan(userId, duration);
        return  createdOwnerPlan;
    }

    @GetMapping("/{ownerPlanId}")
    public SubscriptionDetails getOwnerPlan(@PathVariable long ownerPlanId){
         return ownerPlanService2.getOwnerPlan(ownerPlanId);
    }
}

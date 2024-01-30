package com.nobroker.scheduler;

import com.nobroker.entity.SubscriptionDetails;
import com.nobroker.repository.SubscriptionPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
@Component
public class ExpirationScheduler {
    @Autowired
    private SubscriptionPlanRepository ownerPlanRepository2;

    @Scheduled(cron ="0 0 0 * * *")
    public void checkExpiration(){


      List<SubscriptionDetails> activeplans =ownerPlanRepository2.findBySubscriptionActiveTrueAndSubscriptionExpirationDateBefore(LocalDate.now());
     for (SubscriptionDetails plan : activeplans){
         plan.setSubscriptionActive(false);
         ownerPlanRepository2.save(plan);

     }
    }

}

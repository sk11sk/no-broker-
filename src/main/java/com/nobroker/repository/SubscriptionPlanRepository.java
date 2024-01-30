package com.nobroker.repository;

import com.nobroker.entity.SubscriptionDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SubscriptionPlanRepository extends JpaRepository<SubscriptionDetails, Long> {
    // You can add custom queries if needed



  List<SubscriptionDetails>findBySubscriptionActiveTrueAndSubscriptionExpirationDateBefore(LocalDate date );

    List<SubscriptionDetails>findBySubscriptionExpirationDateBefore(LocalDate date );

}

package com.nobroker.entity;





import jakarta.persistence.*;

import lombok.Data;


import java.time.LocalDate;

@Entity
@Table(name="owner_plan")
@Data
public class SubscriptionDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "owner_plan_id")
    private long ownerPlanId;

    @Column(name = "user_id",unique = true)
    private long userId;

    @Column(name = "subscription_active")
    private boolean subscriptionActive;

    @Column(name = "subscription_active_date")
    private LocalDate subscriptionActiveDate;

    @Column(name = "subscription_expiration_date")
    private LocalDate subscriptionExpirationDate;

    @Column(name = "number_of_days")
    private int numberOfDays;
}
package com.gym.subscription_plan.domain;

import com.gym.ddd.Repository;

import java.util.List;

public interface SubscriptionPlanRepository extends Repository {
    SubscriptionPlanId next();

    void save(SubscriptionPlan subscriptionPlan);

    SubscriptionPlan findById(SubscriptionPlanId subscriptionPlanId);

    List<SubscriptionPlan> findAll();
}

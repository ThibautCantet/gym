package com.gym.subscription_plan.domain;

import com.gym.ddd.Repository;

public interface SubscriptionPlanRepository extends Repository {
    SubscriptionPlanId next();

    void save(SubscriptionPlan subscriptionPlan);

    SubscriptionPlan findById(SubscriptionPlanId subscriptionPlanId);
}

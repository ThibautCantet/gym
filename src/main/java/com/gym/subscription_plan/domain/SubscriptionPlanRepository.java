package com.gym.subscription_plan.domain;

public interface SubscriptionPlanRepository {
    SubscriptionPlanId next();

    void save(SubscriptionPlan subscriptionPlan);

    SubscriptionPlan findById(SubscriptionPlanId subscriptionPlanId);
}

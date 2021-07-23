package com.gym.domain.subscription_plan;

public interface SubscriptionPlanRepository {
    SubscriptionPlanId next();

    void save(SubscriptionPlan subscriptionPlan);

    SubscriptionPlan findById(SubscriptionPlanId subscriptionPlanId);
}

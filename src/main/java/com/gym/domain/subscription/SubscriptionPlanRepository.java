package com.gym.domain.subscription;

public interface SubscriptionPlanRepository {
    SubscriptionPlanId next();

    void save(SubscriptionPlan subscriptionPlan);

    SubscriptionPlan findById(SubscriptionPlanId subscriptionPlanId);
}

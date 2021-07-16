package com.gym.domain.subscription;

public interface SubscriptionPlanRepository {

    void save(SubscriptionPlan subscriptionPlan);

    SubscriptionPlan findById(SubscriptionPlanId subscriptionPlanId);
}

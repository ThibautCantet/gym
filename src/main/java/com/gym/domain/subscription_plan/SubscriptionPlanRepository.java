package com.gym.domain.subscription_plan;

public interface SubscriptionPlanRepository {

    void save(SubscriptionPlan subscriptionPlan);

    SubscriptionPlanId next();

    SubscriptionPlan findById(SubscriptionPlanId id);
}

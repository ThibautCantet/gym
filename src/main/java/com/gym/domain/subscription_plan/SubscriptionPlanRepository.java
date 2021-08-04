package com.gym.domain.subscription_plan;

import java.util.UUID;

public interface SubscriptionPlanRepository {

    void save(SubscriptionPlan subscriptionPlan);

    UUID next();

    SubscriptionPlan findById(UUID id);
}

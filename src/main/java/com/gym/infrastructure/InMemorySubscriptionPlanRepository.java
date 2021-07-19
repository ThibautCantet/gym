package com.gym.infrastructure;

import com.gym.domain.subscription_plan.SubscriptionPlan;
import com.gym.domain.subscription_plan.SubscriptionPlanRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemorySubscriptionPlanRepository implements SubscriptionPlanRepository {
    private final List<SubscriptionPlan> subscriptionPlans;

    public InMemorySubscriptionPlanRepository() {
        subscriptionPlans = new ArrayList<>();
    }

    public List<SubscriptionPlan> getAllSubscriptionPlan() {
        return subscriptionPlans;
    }

    @Override
    public void save(SubscriptionPlan subscriptionPlan) {
        subscriptionPlans.add(subscriptionPlan);
    }
}

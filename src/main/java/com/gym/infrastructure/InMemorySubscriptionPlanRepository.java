package com.gym.infrastructure;

import com.gym.domain.subscription.SubscriptionPlan;
import com.gym.domain.subscription.SubscriptionPlanId;
import com.gym.domain.subscription.SubscriptionPlanRepository;

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

    @Override
    public SubscriptionPlan findById(SubscriptionPlanId subscriptionPlanId) {
        return subscriptionPlans.stream().
                filter(subscriptionPlan -> subscriptionPlan.getId().equals(subscriptionPlanId))
                .findFirst()
                .orElse(null);
    }
}

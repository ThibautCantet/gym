package com.gym.infrastructure;

import com.gym.domain.subscription_plan.SubscriptionPlan;
import com.gym.domain.subscription_plan.SubscriptionPlanRepository;

import java.util.*;

public class InMemorySubscriptionPlanRepository implements SubscriptionPlanRepository {
    public final Map<UUID, SubscriptionPlan> subscriptionPlans;
    private UUID fixedId;

    public InMemorySubscriptionPlanRepository(UUID fixedId) {
        this.fixedId = fixedId;
        subscriptionPlans = new HashMap<>();
    }

    public List<SubscriptionPlan> getAllSubscriptionPlan() {
        return subscriptionPlans.values().stream().toList();
    }

    @Override
    public void save(SubscriptionPlan subscriptionPlan) {
        subscriptionPlans.put(subscriptionPlan.getId(), subscriptionPlan);
    }

    @Override
    public UUID next() {
        return fixedId;
    }

    @Override
    public SubscriptionPlan findById(UUID id) {
        return subscriptionPlans.get(id);
    }
}

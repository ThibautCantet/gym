package com.gym.infrastructure;

import com.gym.domain.subscription_plan.SubscriptionPlan;
import com.gym.domain.subscription_plan.SubscriptionPlanId;
import com.gym.domain.subscription_plan.SubscriptionPlanRepository;

import java.util.*;

public class InMemorySubscriptionPlanRepository implements SubscriptionPlanRepository {
    public final Map<SubscriptionPlanId, SubscriptionPlan> subscriptionPlans;
    private SubscriptionPlanId fixedId;

    public InMemorySubscriptionPlanRepository(UUID fixedId) {
        this.fixedId = new SubscriptionPlanId(fixedId);
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
    public SubscriptionPlanId next() {
        return fixedId;
    }

    @Override
    public SubscriptionPlan findById(SubscriptionPlanId id) {
        return subscriptionPlans.get(id);
    }
}

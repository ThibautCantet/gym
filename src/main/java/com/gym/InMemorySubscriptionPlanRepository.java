package com.gym;

import java.util.ArrayList;
import java.util.List;

public class InMemorySubscriptionPlanRepository implements SubscriptionPlanRepository {
    private final List<SubscriptionPlan> subscriptionPlans;

    public InMemorySubscriptionPlanRepository() {
        subscriptionPlans = new ArrayList<>();
    }

    List<SubscriptionPlan> getAllSubscriptionPlan() {
        return subscriptionPlans;
    }

    @Override
    public void save(SubscriptionPlan subscriptionPlan) {
        subscriptionPlans.add(subscriptionPlan);
    }
}

package com.gym.infrastructure;

import com.gym.domain.subscriber.SubscriptionId;
import com.gym.domain.subscription.SubscriptionPlan;
import com.gym.domain.subscription.SubscriptionPlanId;
import com.gym.domain.subscription.SubscriptionPlanRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class InMemorySubscriptionPlanRepository implements SubscriptionPlanRepository {
    private final List<SubscriptionPlan> subscriptionPlans;
    private final UUID fixedUUID;

    public InMemorySubscriptionPlanRepository(UUID fixedUUID) {
        this.fixedUUID = fixedUUID;
        subscriptionPlans = new ArrayList<>();
    }

    @Override
    public SubscriptionPlanId next() {
        return new SubscriptionPlanId(fixedUUID);
    }

    public List<SubscriptionPlan> getAllSubscriptionPlan() {
        return subscriptionPlans;
    }

    public void add(SubscriptionPlan subscriptionPlan) {
        subscriptionPlans.add(subscriptionPlan);
    }

    @Override
    public void save(SubscriptionPlan subscriptionPlan) {
        final Optional<SubscriptionPlan> exists = subscriptionPlans.stream()
                .filter(plan -> plan.getId().equals(subscriptionPlan.getId()))
                .findFirst();
        if (exists.isPresent()) {
            subscriptionPlans.remove(exists.get());
        }
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

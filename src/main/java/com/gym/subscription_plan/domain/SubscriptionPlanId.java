package com.gym.subscription_plan.domain;

import com.gym.ddd.AggregateId;

import java.util.UUID;

public record SubscriptionPlanId(UUID uuid) implements AggregateId {
    public SubscriptionPlanId(String subscriptionPlanId) {
        this(UUID.fromString(subscriptionPlanId));
    }
}

package com.gym.subscription_plan.domain;

import java.util.UUID;

public record SubscriptionPlanId(UUID uuid) {
    public SubscriptionPlanId(String subscriptionPlanId) {
        this(UUID.fromString(subscriptionPlanId));
    }
}

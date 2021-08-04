package com.gym.domain.subscription_plan;

import java.util.UUID;

public record SubscriptionPlanId(String id) {
    public SubscriptionPlanId(UUID id) {
        this(id.toString());
    }

    @Override
    public String toString() {
        return id;
    }
}

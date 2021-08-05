package com.gym.subscription_plan.use_case;

import java.util.UUID;

public record GetSubscriptionPlanCommand(UUID subscriptionId) {
    public GetSubscriptionPlanCommand(String subscriptionId) {
        this(UUID.fromString(subscriptionId));
    }
}

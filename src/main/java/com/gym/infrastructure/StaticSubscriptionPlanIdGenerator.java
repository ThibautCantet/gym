package com.gym.infrastructure;

import com.gym.domain.subscription.SubscriptionPlanIdGenerator;
import com.gym.domain.subscription.SubscriptionPlanId;

import java.util.UUID;

public class StaticSubscriptionPlanIdGenerator implements SubscriptionPlanIdGenerator {
    private UUID fixedUUID;

    public StaticSubscriptionPlanIdGenerator(UUID fixedUUID) {
        this.fixedUUID = fixedUUID;
    }

    @Override
    public SubscriptionPlanId next() {
        return new SubscriptionPlanId(fixedUUID);
    }
}

package com.gym.infrastructure;

import com.gym.domain.subscriber.SubscriptionId;
import com.gym.domain.subscriber.SubscriptionIdGenerator;

import java.util.UUID;

public class StaticSubscriptionIdGenerator implements SubscriptionIdGenerator {
    private final UUID fixedUUID;

    public StaticSubscriptionIdGenerator(UUID fixedUUID) {
        this.fixedUUID = fixedUUID;
    }

    @Override
    public SubscriptionId next() {
        return new SubscriptionId(fixedUUID);
    }
}

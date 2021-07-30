package com.gym.subscription.domain;

import com.gym.ddd.AggregateId;

import java.util.UUID;

public record SubscriptionId(UUID uuid) implements AggregateId {
}

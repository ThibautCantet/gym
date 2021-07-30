package com.gym.membership.domain;

import com.gym.ddd.AggregateId;

import java.util.UUID;

public record MemberId(UUID id) implements AggregateId {
    public MemberId(String id) {
        this(UUID.fromString(id));
    }
}

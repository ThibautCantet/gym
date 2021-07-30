package com.gym.membership.domain;

import java.util.UUID;

public record MemberId(UUID id) {
    public MemberId(String id) {
        this(UUID.fromString(id));
    }
}

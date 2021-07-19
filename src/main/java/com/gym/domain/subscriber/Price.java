package com.gym.domain.subscriber;

public record Price(Double value) {
    public Price applyDiscount(boolean isStudent) {
        if (isStudent) {
            return new Price(this.value * (1 - 20d / 100));
        } else {
            return this;
        }
    }
}

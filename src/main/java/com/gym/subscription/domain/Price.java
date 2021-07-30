package com.gym.subscription.domain;

public record Price(Double value) {
    public Price applyDiscount(boolean isStudent) {
        if (isStudent) {
            return new Price(this.value * (1 - 20d / 100));
        } else {
            return this;
        }
    }

    public Price applyThreeAnniversaryDiscount() {
        return new Price(this.value * (1 - 5d / 100));
    }
}

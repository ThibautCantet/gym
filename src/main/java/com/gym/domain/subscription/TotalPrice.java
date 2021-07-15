package com.gym.domain.subscription;

public record TotalPrice(Double value) {
    public TotalPrice applyDiscount(Double discountRate) {
        return new TotalPrice(this.value * (1 - discountRate / 100));
    }
}

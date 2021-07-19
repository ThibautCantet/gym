package com.gym.domain.subscription_plan;

public record TotalPrice(Double value) {
    public TotalPrice applyDiscount(DiscountRate discountRate) {
        return new TotalPrice(this.value * (1 - discountRate.getValue() / 100));
    }
}

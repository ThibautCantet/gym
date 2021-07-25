package com.gym.subscription_plan.domain;

public record TotalPrice(Double value) {
    public TotalPrice applyDiscount(DiscountRate discountRate) {
        if (discountRate.canApplyRate()) {
            return new TotalPrice(this.value * (1 - discountRate.getRate() / 100));
        } else {
            return this;
        }
    }
}

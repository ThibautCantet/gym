package com.gym.domain.subscriber;

import com.gym.domain.subscription_plan.DiscountRate;

public record Price(double value) {
    public Price apply(DiscountRate discountRate) {
        return new Price(value * (1 - discountRate.getValue() / 100d));
    }
}

package com.gym.domain.subscription_plan;

public class DiscountRate {
    private final Double value;

    public DiscountRate(Period period) {
        value = period.equals(Period.Montly) ? 0d: 10d;
    }

    public DiscountRate(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }
}

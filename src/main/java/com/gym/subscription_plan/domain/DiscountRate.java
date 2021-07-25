package com.gym.subscription_plan.domain;

public record DiscountRate(Double rate) {
    public DiscountRate(Period period) {
        this(initializeRate(period));
    }

    private static double initializeRate(Period period) {
        return period.equals(Period.Montly) ? 0d : 10d;
    }

    public double getRate() {
        return rate;
    }

    Boolean canApplyRate() {
        return rate != 0;
    }
}

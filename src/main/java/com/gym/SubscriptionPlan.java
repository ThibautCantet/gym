package com.gym;

public class SubscriptionPlan {
    private BasePrice basePrice;
    private Period period;

    public SubscriptionPlan(BasePrice basePrice, Period period) {
        this.basePrice = basePrice;
        this.period = period;
    }

    public BasePrice getBasePrice() {
        return basePrice;
    }

    public Period getPeriod() {
        return period;
    }
}

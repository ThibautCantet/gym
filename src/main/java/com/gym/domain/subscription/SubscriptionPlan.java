package com.gym.domain.subscription;

public class SubscriptionPlan {
    private final BasePrice basePrice;
    private final Period period;
    private final Double discountRate;
    private TotalPrice totalPrice;

    public SubscriptionPlan(BasePrice basePrice, Period period) {
        this.basePrice = basePrice;
        this.period = period;
        this.discountRate = period.equals(Period.Montly) ? 0d: 10d;
        this.totalPrice = new TotalPrice(basePrice.amount());
        this.totalPrice = this.totalPrice.applyDiscount(discountRate);
    }

    public BasePrice getBasePrice() {
        return basePrice;
    }

    public Period getPeriod() {
        return period;
    }

    public Double getDiscountRate() {
        return discountRate;
    }

    public TotalPrice getTotalPrice() {
        return totalPrice;
    }
}

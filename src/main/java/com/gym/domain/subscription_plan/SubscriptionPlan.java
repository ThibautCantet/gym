package com.gym.domain.subscription_plan;

public class SubscriptionPlan {
    private final BasePrice basePrice;
    private final Period period;
    private final DiscountRate discountRate;
    private TotalPrice totalPrice;

    public SubscriptionPlan(BasePrice basePrice, Period period) {
        this.basePrice = basePrice;
        this.period = period;
        this.discountRate = new DiscountRate(period);
        this.totalPrice = new TotalPrice(basePrice.amount());
        this.totalPrice = this.totalPrice.applyDiscount(discountRate);
    }

    public BasePrice getBasePrice() {
        return basePrice;
    }

    public Period getPeriod() {
        return period;
    }

    public DiscountRate getDiscountRate() {
        return discountRate;
    }

    public TotalPrice getTotalPrice() {
        return totalPrice;
    }
}

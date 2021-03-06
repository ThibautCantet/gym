package com.gym.domain.subscription_plan;

public class SubscriptionPlan {
    private final SubscriptionPlanId id;
    private final BasePrice basePrice;
    private final Period period;
    private final DiscountRate discountRate;
    private TotalPrice totalPrice;

    public SubscriptionPlan(SubscriptionPlanId id, BasePrice basePrice, Period period) {
        this.id = id;
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

    public SubscriptionPlanId getId() {
        return id;
    }

    public void changePrice(double newPrice) {
        this.totalPrice = new TotalPrice(newPrice);
    }
}

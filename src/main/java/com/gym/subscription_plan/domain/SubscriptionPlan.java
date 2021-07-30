package com.gym.subscription_plan.domain;

public class SubscriptionPlan {
    private final SubscriptionPlanId id;
    private final BasePrice basePrice;
    private final Period period;
    private final DiscountRate discountRate;
    private final TotalPrice totalPrice;

    private SubscriptionPlan(SubscriptionPlanId subscriptionPlanId, double basePrice, Period period) {
        this.id = subscriptionPlanId;
        this.basePrice = new BasePrice(basePrice);
        this.period = period;
        this.discountRate = new DiscountRate(period);
        this.totalPrice = initializeTotalPrice(this.basePrice);
    }

    public static SubscriptionPlan createMonthly(SubscriptionPlanId subscriptionPlanId, double basePrice) {
        return new SubscriptionPlan(subscriptionPlanId, basePrice, Period.Monthly);
    }

    public static SubscriptionPlan createYearly(SubscriptionPlanId subscriptionPlanId, double basePrice) {
        return new SubscriptionPlan(subscriptionPlanId, basePrice, Period.Yearly);
    }

    private TotalPrice initializeTotalPrice(BasePrice basePrice) {
        final TotalPrice totalPrice = new TotalPrice(basePrice.amount());
        return totalPrice.applyDiscount(discountRate);
    }

    public SubscriptionPlanId getId() {
        return id;
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

    public SubscriptionPlan changePrice(double basePrice) {
        return new SubscriptionPlan(this.id, basePrice, this.period);
    }
}

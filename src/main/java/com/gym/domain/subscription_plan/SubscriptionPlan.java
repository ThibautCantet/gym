package com.gym.domain.subscription_plan;

public class SubscriptionPlan {
    private final SubscriptionPlanId id;
    private final BasePrice basePrice;
    private final Period period;
    private final DiscountRate discountRate;
    private final TotalPrice totalPrice;

    private SubscriptionPlan(SubscriptionPlanId subscriptionPlanId, BasePrice basePrice, Period period) {
        this.id = subscriptionPlanId;
        this.basePrice = basePrice;
        this.period = period;
        this.discountRate = new DiscountRate(period);
        this.totalPrice = initializeTotalPrice(basePrice);
    }

    public static SubscriptionPlan createMonthly(SubscriptionPlanId subscriptionPlanId, BasePrice basePrice) {
        return new SubscriptionPlan(subscriptionPlanId, basePrice, Period.Montly);
    }

    public static SubscriptionPlan createYearly(SubscriptionPlanId subscriptionPlanId, BasePrice basePrice) {
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

    public SubscriptionPlan changePrice(BasePrice basePrice) {
        return new SubscriptionPlan(this.id, basePrice, this.period);
    }
}
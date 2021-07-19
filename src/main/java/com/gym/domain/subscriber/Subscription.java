package com.gym.domain.subscriber;

import com.gym.domain.subscription_plan.DiscountRate;
import com.gym.domain.subscription_plan.SubscriptionPlan;

import java.util.Date;

public class Subscription {
    private Date date;
    private SubscriptionPlan subscriptionPlan;
    private Subscriber subscriber;
    private DiscountRate discountRate;
    private Price price;

    public Subscription(SubscriptionPlan subscriptionPlan, Subscriber subscriber) {
        this.discountRate = subscriber.isStudent() ?
                new DiscountRate(20d) :
                new DiscountRate(0d);
        price = new Price(subscriptionPlan.getTotalPrice().value());
        price = price.apply(discountRate);

        if (!subscriber.isStudent()) {
            this.discountRate = subscriptionPlan.getDiscountRate();
        }
    }

    public DiscountRate getDiscountRate() {
        return discountRate;
    }

    public Price getPrice() {
        return price;
    }
}

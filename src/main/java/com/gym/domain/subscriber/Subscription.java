package com.gym.domain.subscriber;

import com.gym.domain.subscription.BasePrice;
import com.gym.domain.subscription.SubscriptionPlanId;

import java.time.Clock;
import java.time.LocalDateTime;

public class Subscription {
    private final SubscriptionId subscriptionId;
    private final SubscriptionPlanId subscriptionPlanId;
    private final SubscriptionDate subscriptionDate;
    private final Subscriber subscriber;
    private final Price price;

    public Subscription(SubscriptionId subscriptionId, SubscriptionPlanId subscriptionPlanId, BasePrice basePrice, Subscriber subscriber, Clock clock) {
        this.subscriptionId = subscriptionId;
        this.subscriptionPlanId = subscriptionPlanId;
        this.subscriber = subscriber;
        this.subscriptionDate = new SubscriptionDate(LocalDateTime.now(clock));
        this.price = new Price(basePrice.amount());
    }

    public SubscriptionId getSubscriptionId() {
        return subscriptionId;
    }

    public SubscriptionPlanId getSubscriptionPlanId() {
        return subscriptionPlanId;
    }

    public SubscriptionDate getSubscriptionDate() {
        return subscriptionDate;
    }

    public Price getPrice() {
        return price;
    }
}

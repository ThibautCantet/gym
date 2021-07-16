package com.gym.domain.subscriber;

import com.gym.domain.subscription.SubscriptionPlanId;
import com.gym.domain.subscription.TotalPrice;

import java.time.Clock;
import java.time.LocalDateTime;

public class Subscription {
    private final SubscriptionId subscriptionId;
    private final SubscriptionPlanId subscriptionPlanId;
    private final SubscriptionDate subscriptionDate;
    private final Subscriber subscriber;
    private final Price price;

    public Subscription(SubscriptionId subscriptionId, SubscriptionPlanId subscriptionPlanId, TotalPrice totalPrice, Subscriber subscriber, Clock clock) {
        this.subscriptionId = subscriptionId;
        this.subscriptionPlanId = subscriptionPlanId;
        this.subscriber = subscriber;
        this.subscriptionDate = new SubscriptionDate(LocalDateTime.now(clock));
        this.price = new Price(totalPrice.value());
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

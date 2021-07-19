package com.gym.domain.subscriber;

import com.gym.domain.subscription.SubscriptionPlanId;
import com.gym.domain.subscription.TotalPrice;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Subscription {
    private final SubscriptionId subscriptionId;
    private final SubscriptionPlanId subscriptionPlanId;
    private final SubscriberId subscriberId;
    private final SubscriptionDate subscriptionDate;
    private final Price price;

    private Subscription(SubscriptionId subscriptionId, SubscriptionPlanId subscriptionPlanId, TotalPrice totalPrice, Subscriber subscriber, Clock clock) {
        this.subscriptionId = subscriptionId;
        this.subscriptionPlanId = subscriptionPlanId;
        this.subscriberId = subscriber.getId();
        this.subscriptionDate = new SubscriptionDate(LocalDateTime.now(clock));
        this.price = initializePriceWithDiscount(totalPrice, subscriber);
    }

    private Price initializePriceWithDiscount(TotalPrice totalPrice, Subscriber subscriber) {
        final Price price = new Price(totalPrice.value());
        return price.applyDiscount(subscriber.isStudent());
    }

    public static Subscription subscribe(SubscriptionId subscriptionId, SubscriptionPlanId subscriptionPlanId, TotalPrice totalPrice, Subscriber subscriber, Clock clock) {
        return new Subscription(subscriptionId, subscriptionPlanId, totalPrice, subscriber, clock);
    }

    public SubscriptionId getSubscriptionId() {
        return subscriptionId;
    }

    public SubscriptionPlanId getSubscriptionPlanId() {
        return subscriptionPlanId;
    }

    public SubscriberId getSubscriberId() {
        return subscriberId;
    }

    public SubscriptionDate getSubscriptionDate() {
        return subscriptionDate;
    }

    public Price getPrice() {
        return price;
    }

    public boolean isOnGoing(LocalDate localDate) {
        return subscriptionDate.isOnGoing(localDate);
    }
}

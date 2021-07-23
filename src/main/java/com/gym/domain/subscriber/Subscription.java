package com.gym.domain.subscriber;

import com.gym.domain.membership.Subscriber;
import com.gym.domain.membership.SubscriberId;
import com.gym.domain.subscription.Period;
import com.gym.domain.subscription.SubscriptionPlanId;
import com.gym.domain.subscription.TotalPrice;

import java.time.Clock;
import java.time.LocalDate;

public class Subscription {
    private final SubscriptionId subscriptionId;
    private final SubscriptionPlanId subscriptionPlanId;
    private final Period period;
    private final SubscriberId subscriberId;
    private final SubscriptionDate subscriptionDate;
    private final Price price;

    private Subscription(SubscriptionId subscriptionId,
                         SubscriptionPlanId subscriptionPlanId,
                         Period period,
                         TotalPrice totalPrice,
                         Subscriber subscriber,
                         Clock clock) {
        this.subscriptionId = subscriptionId;
        this.subscriptionPlanId = subscriptionPlanId;
        this.period = period;
        this.subscriberId = subscriber.getId();
        final LocalDate today = LocalDate.now(clock);
        this.subscriptionDate = initializeSubscriptionDate(today, period);
        this.price = initializePriceWithDiscount(totalPrice, subscriber);
    }

    private Subscription(SubscriptionId subscriptionId, SubscriptionPlanId subscriptionPlanId, Period period, SubscriberId subscriberId, SubscriptionDate subscriptionDate, Price price) {
        this.subscriptionId = subscriptionId;
        this.subscriptionPlanId = subscriptionPlanId;
        this.period = period;
        this.subscriberId = subscriberId;
        this.subscriptionDate = subscriptionDate;
        this.price = price;
    }

    private SubscriptionDate initializeSubscriptionDate(LocalDate today, Period period) {
        final LocalDate endDate = period.equals(Period.Montly) ? today.plusMonths(1) : today.plusYears(1);
        return new SubscriptionDate(today, endDate);
    }

    private Price initializePriceWithDiscount(TotalPrice totalPrice, Subscriber subscriber) {
        final Price price = new Price(totalPrice.value());
        return price.applyDiscount(subscriber.isStudent());
    }

    public static Subscription subscribe(SubscriptionId subscriptionId, SubscriptionPlanId subscriptionPlanId, Period monthlyPeriod, TotalPrice totalPrice, Subscriber subscriber, Clock clock) {
        return new Subscription(subscriptionId, subscriptionPlanId, monthlyPeriod, totalPrice, subscriber, clock);
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

    public boolean isToRenew(LocalDate date) {
        return period.equals(Period.Montly) && isOnGoing(date);
    }

    public Subscription renew() {
        return  new Subscription(this.subscriptionId,
        this.subscriptionPlanId,
        this.period,
        this.subscriberId,
        this.subscriptionDate.renew(),
        this.price);
    }
}

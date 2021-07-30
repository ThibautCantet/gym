package com.gym.subscription.domain;

import com.gym.subscription_plan.domain.Period;
import com.gym.subscription_plan.domain.TotalPrice;

import java.time.Clock;
import java.time.LocalDate;

public class Subscription {
    private final SubscriptionId subscriptionId;
    private final Period period;
    private final SubscriptionDate subscriptionDate;
    private final Price price;

    private Subscription(SubscriptionId subscriptionId,
                         Period period,
                         TotalPrice totalPrice,
                         boolean isStudent,
                         Clock clock) {
        this.subscriptionId = subscriptionId;
        this.period = period;
        final LocalDate today = LocalDate.now(clock);
        this.subscriptionDate = initializeSubscriptionDate(today, period);
        this.price = initializePriceWithDiscount(totalPrice, isStudent);
    }

    private Subscription(SubscriptionId subscriptionId,
                         Period period,
                         SubscriptionDate subscriptionDate,
                         Price price) {
        this.subscriptionId = subscriptionId;
        this.period = period;
        this.subscriptionDate = subscriptionDate;
        this.price = price;
    }

    private SubscriptionDate initializeSubscriptionDate(LocalDate today, Period period) {
        final LocalDate endDate = period.equals(Period.Monthly) ? today.plusMonths(1) : today.plusYears(1);
        return new SubscriptionDate(today, endDate);
    }

    private Price initializePriceWithDiscount(TotalPrice totalPrice, boolean isStudent) {
        final Price price = new Price(totalPrice.value());
        return price.applyDiscount(isStudent);
    }

    public static Subscription subscribe(SubscriptionId subscriptionId, Period monthlyPeriod, TotalPrice totalPrice, boolean isStudent, Clock clock) {
        return new Subscription(subscriptionId, monthlyPeriod, totalPrice, isStudent, clock);
    }

    public SubscriptionId getSubscriptionId() {
        return subscriptionId;
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
        return period.equals(Period.Monthly) && isOnGoing(date);
    }

    public Subscription renew() {
        return new Subscription(this.subscriptionId,
        this.period,
        this.subscriptionDate.renew(),
        this.price);
    }

    public boolean hasItThirdAnniversary(LocalDate today) {
        return subscriptionDate.hasItThirdAnniversary(today);
    }

    public Subscription applyThirdAnniversaryDiscount() {
        final Price newPrice = this.price.applyThreeAnniversaryDiscount();
        return new Subscription(this.subscriptionId,
                this.period,
                this.subscriptionDate,
                newPrice);
    }
}

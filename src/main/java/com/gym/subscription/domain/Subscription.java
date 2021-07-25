package com.gym.subscription.domain;

import com.gym.membership.domain.Member;
import com.gym.membership.domain.MemberId;
import com.gym.subscription_plan.domain.Period;
import com.gym.subscription_plan.domain.SubscriptionPlanId;
import com.gym.subscription_plan.domain.TotalPrice;

import java.time.Clock;
import java.time.LocalDate;

public class Subscription {
    private final SubscriptionId subscriptionId;
    private final SubscriptionPlanId subscriptionPlanId;
    private final Period period;
    private final MemberId memberId;
    private final SubscriptionDate subscriptionDate;
    private final Price price;

    private Subscription(SubscriptionId subscriptionId,
                         SubscriptionPlanId subscriptionPlanId,
                         Period period,
                         TotalPrice totalPrice,
                         Member member,
                         Clock clock) {
        this.subscriptionId = subscriptionId;
        this.subscriptionPlanId = subscriptionPlanId;
        this.period = period;
        this.memberId = member.getId();
        final LocalDate today = LocalDate.now(clock);
        this.subscriptionDate = initializeSubscriptionDate(today, period);
        this.price = initializePriceWithDiscount(totalPrice, member);
    }

    private Subscription(SubscriptionId subscriptionId, SubscriptionPlanId subscriptionPlanId, Period period, MemberId memberId, SubscriptionDate subscriptionDate, Price price) {
        this.subscriptionId = subscriptionId;
        this.subscriptionPlanId = subscriptionPlanId;
        this.period = period;
        this.memberId = memberId;
        this.subscriptionDate = subscriptionDate;
        this.price = price;
    }

    private SubscriptionDate initializeSubscriptionDate(LocalDate today, Period period) {
        final LocalDate endDate = period.equals(Period.Montly) ? today.plusMonths(1) : today.plusYears(1);
        return new SubscriptionDate(today, endDate);
    }

    private Price initializePriceWithDiscount(TotalPrice totalPrice, Member member) {
        final Price price = new Price(totalPrice.value());
        return price.applyDiscount(member.isStudent());
    }

    public static Subscription subscribe(SubscriptionId subscriptionId, SubscriptionPlanId subscriptionPlanId, Period monthlyPeriod, TotalPrice totalPrice, Member member, Clock clock) {
        return new Subscription(subscriptionId, subscriptionPlanId, monthlyPeriod, totalPrice, member, clock);
    }

    public SubscriptionId getSubscriptionId() {
        return subscriptionId;
    }

    public SubscriptionPlanId getSubscriptionPlanId() {
        return subscriptionPlanId;
    }

    public MemberId getSubscriberId() {
        return memberId;
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
        this.memberId,
        this.subscriptionDate.renew(),
        this.price);
    }
}

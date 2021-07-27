package com.gym.use_case;

import com.gym.domain.subscriber.Price;
import com.gym.domain.subscriber.Subscription;
import com.gym.domain.subscriber.SubscriptionRepository;

import java.time.Month;

public class ShowOnGoingSubscriptionsAmount {
    private final SubscriptionRepository subscriptionRepository;

    public ShowOnGoingSubscriptionsAmount(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public Double execute(Month month, int year) {
        return subscriptionRepository.findAll().stream()
                .filter(subscription -> subscription.isOnGoing(month, year))
                .map(Subscription::getPrice)
                .map(Price::value)
                .reduce(Double::sum)
                .orElse(0d);
    }
}

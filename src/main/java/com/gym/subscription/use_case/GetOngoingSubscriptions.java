package com.gym.subscription.use_case;

import com.gym.subscription.domain.Price;
import com.gym.subscription.domain.Subscription;
import com.gym.subscription.domain.SubscriptionRepository;

import java.time.LocalDate;

public class GetOngoingSubscriptions {
    private final SubscriptionRepository subscriptionRepository;

    public GetOngoingSubscriptions(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public Double execute(LocalDate localDate) {
        return subscriptionRepository.findAll()
                .stream()
                .filter(subscription -> subscription.isOnGoing(localDate))
                .map(Subscription::getPrice)
                .map(Price::value)
                .reduce(Double::sum)
                .orElse(0d);
    }
}

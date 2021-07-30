package com.gym.subscription.use_case;

import com.gym.subscription.domain.Subscription;
import com.gym.subscription.domain.SubscriptionRepository;

import java.time.Clock;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class RenewMonthlySubscription {
    private final SubscriptionRepository subscriptionRepository;
    private final Clock clock;

    public RenewMonthlySubscription(SubscriptionRepository subscriptionRepository, Clock clock) {
        this.subscriptionRepository = subscriptionRepository;
        this.clock = clock;
    }

    public void handle() {
        final List<Subscription> subscriptionsToRenew = subscriptionRepository.findAll().stream()
                .filter(subscription -> subscription.isToRenew(LocalDate.now(clock)))
                .map(Subscription::renew)
                .collect(Collectors.toList());

        subscriptionRepository.saveAll(subscriptionsToRenew);
    }
}

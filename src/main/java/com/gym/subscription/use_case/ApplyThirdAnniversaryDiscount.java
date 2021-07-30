package com.gym.subscription.use_case;

import com.gym.subscription.domain.Subscription;
import com.gym.subscription.domain.SubscriptionRepository;

import java.time.Clock;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ApplyThirdAnniversaryDiscount {
    private final SubscriptionRepository subscriptionRepository;
    private final Clock clock;

    public ApplyThirdAnniversaryDiscount(SubscriptionRepository subscriptionRepository, Clock clock) {
        this.subscriptionRepository = subscriptionRepository;
        this.clock = clock;
    }

    public void execute() {
        final List<Subscription> subscriptionsToUpdate = subscriptionRepository.findAll()
                .stream()
                .filter(subscription -> subscription.hasItThirdAnniversary(LocalDate.now(clock)))
                .collect(Collectors.toList());

        final List<Subscription> subscriptionsToSave = subscriptionsToUpdate.stream()
                .map(Subscription::applyThirdAnniversaryDiscount)
                .collect(Collectors.toList());

        subscriptionRepository.saveAll(subscriptionsToSave);
    }
}

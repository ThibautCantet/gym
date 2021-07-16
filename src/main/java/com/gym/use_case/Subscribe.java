package com.gym.use_case;

import com.gym.domain.subscriber.*;
import com.gym.domain.subscription.*;

import java.time.Clock;

public class Subscribe {

    private final SubscriptionPlanRepository subscriptionPlanRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final Clock clock;

    public Subscribe(SubscriptionPlanRepository subscriptionPlanRepository, SubscriptionRepository subscriptionRepository, Clock clock) {
        this.subscriptionPlanRepository = subscriptionPlanRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.clock = clock;
    }

    public void execute(Subscriber subscriber, SubscriptionPlanId subscriptionPlanId) {
        final SubscriptionId subscriptionId = subscriptionRepository.next();
        final SubscriptionPlan subscriptionPlan = subscriptionPlanRepository.findById(subscriptionPlanId);
        final TotalPrice basePrice = subscriptionPlan.getTotalPrice();

        final Subscription subscription = new Subscription(subscriptionId, subscriptionPlanId, basePrice, subscriber, clock);

        subscriptionRepository.save(subscription);
    }
}
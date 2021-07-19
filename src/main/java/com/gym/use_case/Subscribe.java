package com.gym.use_case;

import com.gym.domain.subscriber.Subscriber;
import com.gym.domain.subscriber.Subscription;
import com.gym.domain.subscriber.SubscriptionRepository;
import com.gym.domain.subscription_plan.SubscriptionPlan;

public class Subscribe {

    private final SubscriptionRepository subscriptionRepository;

    public Subscribe(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public void execute(SubscriptionPlan subscriptionPlan, Subscriber subscriber) {
        subscriptionRepository.save(new Subscription(subscriptionPlan, subscriber));
    }
}

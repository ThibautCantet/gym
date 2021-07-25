package com.gym.subscription.use_case;

import com.gym.subscription.domain.Subscription;
import com.gym.subscription.domain.SubscriptionId;
import com.gym.subscription.domain.SubscriptionRepository;
import com.gym.membership.domain.Member;
import com.gym.domain.subscription_plan.*;

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

    public void execute(Member member, SubscriptionPlanId subscriptionPlanId) {
        final SubscriptionId subscriptionId = subscriptionRepository.next();
        final SubscriptionPlan subscriptionPlan = subscriptionPlanRepository.findById(subscriptionPlanId);
        final TotalPrice basePrice = subscriptionPlan.getTotalPrice();
        final Period period = subscriptionPlan.getPeriod();

        final Subscription subscription = Subscription.subscribe(subscriptionId, subscriptionPlanId, period, basePrice, member, clock);

        subscriptionRepository.save(subscription);
    }
}

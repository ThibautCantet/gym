package com.gym.use_case;

import com.gym.domain.subscription.BasePrice;
import com.gym.domain.subscription.SubscriptionPlan;
import com.gym.domain.subscription.SubscriptionPlanId;
import com.gym.domain.subscription.SubscriptionPlanRepository;

public class ChangeSubscriptionPlanPrice {
    private final SubscriptionPlanRepository subscriptionPlanRepository;

    public ChangeSubscriptionPlanPrice(SubscriptionPlanRepository subscriptionPlanRepository) {
        this.subscriptionPlanRepository = subscriptionPlanRepository;
    }

    public void execute(SubscriptionPlanId subscriptionPlanId, BasePrice price) {
        final SubscriptionPlan subscriptionPlan = subscriptionPlanRepository.findById(subscriptionPlanId);
        final SubscriptionPlan updatedSubscriptionPlan = subscriptionPlan.changePrice(price);

        subscriptionPlanRepository.save(updatedSubscriptionPlan);
    }
}

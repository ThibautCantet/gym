package com.gym.use_case;

import com.gym.domain.subscription_plan.BasePrice;
import com.gym.domain.subscription_plan.SubscriptionPlan;
import com.gym.domain.subscription_plan.SubscriptionPlanId;
import com.gym.domain.subscription_plan.SubscriptionPlanRepository;

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

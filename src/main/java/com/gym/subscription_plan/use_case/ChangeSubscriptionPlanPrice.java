package com.gym.subscription_plan.use_case;

import com.gym.subscription_plan.domain.SubscriptionPlan;
import com.gym.subscription_plan.domain.SubscriptionPlanId;
import com.gym.subscription_plan.domain.SubscriptionPlanRepository;

public class ChangeSubscriptionPlanPrice {
    private final SubscriptionPlanRepository subscriptionPlanRepository;

    public ChangeSubscriptionPlanPrice(SubscriptionPlanRepository subscriptionPlanRepository) {
        this.subscriptionPlanRepository = subscriptionPlanRepository;
    }

    public void execute(SubscriptionPlanId subscriptionPlanId, double price) {
        final SubscriptionPlan subscriptionPlan = subscriptionPlanRepository.findById(subscriptionPlanId);
        final SubscriptionPlan updatedSubscriptionPlan = subscriptionPlan.changePrice(price);

        subscriptionPlanRepository.save(updatedSubscriptionPlan);
    }
}

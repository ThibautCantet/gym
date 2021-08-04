package com.gym.use_case;

import com.gym.domain.subscription_plan.SubscriptionPlan;
import com.gym.domain.subscription_plan.SubscriptionPlanRepository;

import java.util.UUID;

public class ChangeSubscriptionPlanPrice {
    private final SubscriptionPlanRepository subscriptionPlanRepository;

    public ChangeSubscriptionPlanPrice(SubscriptionPlanRepository subscriptionPlanRepository) {
        this.subscriptionPlanRepository = subscriptionPlanRepository;
    }

    public void execute(UUID id, double newPrice) {
        SubscriptionPlan subscriptionPlanToUpdate = subscriptionPlanRepository.findById(id);

        subscriptionPlanToUpdate.changePrice(newPrice);

        subscriptionPlanRepository.save(subscriptionPlanToUpdate);
    }
}

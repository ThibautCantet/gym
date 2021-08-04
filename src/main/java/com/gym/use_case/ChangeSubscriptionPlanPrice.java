package com.gym.use_case;

import com.gym.domain.subscription_plan.SubscriptionPlan;
import com.gym.domain.subscription_plan.SubscriptionPlanId;
import com.gym.domain.subscription_plan.SubscriptionPlanRepository;

import java.util.UUID;

public class ChangeSubscriptionPlanPrice {
    private final SubscriptionPlanRepository subscriptionPlanRepository;

    public ChangeSubscriptionPlanPrice(SubscriptionPlanRepository subscriptionPlanRepository) {
        this.subscriptionPlanRepository = subscriptionPlanRepository;
    }

    public void execute(UUID id, double newPrice) {
        SubscriptionPlan subscriptionPlanToUpdate = subscriptionPlanRepository.findById(new SubscriptionPlanId(id));

        subscriptionPlanToUpdate.changePrice(newPrice);

        subscriptionPlanRepository.save(subscriptionPlanToUpdate);
    }
}

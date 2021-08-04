package com.gym.use_case;

import com.gym.domain.subscription_plan.*;

import java.util.UUID;

public class CreateSubscriptionPlan {
    private final SubscriptionPlanRepository subscriptionPlanRepository;

    public CreateSubscriptionPlan(SubscriptionPlanRepository subscriptionPlanRepository) {
        this.subscriptionPlanRepository = subscriptionPlanRepository;
    }

    public void execute(BasePrice basePrice, Period period) {
        SubscriptionPlanId id = subscriptionPlanRepository.next();
        subscriptionPlanRepository.save(new SubscriptionPlan(id, basePrice, period));
    }
}

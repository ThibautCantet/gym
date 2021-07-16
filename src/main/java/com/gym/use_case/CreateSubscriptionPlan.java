package com.gym.use_case;

import com.gym.domain.subscription.*;

public class CreateSubscriptionPlan {
    private final SubscriptionPlanRepository subscriptionPlanRepository;

    public CreateSubscriptionPlan(SubscriptionPlanRepository subscriptionPlanRepository) {
        this.subscriptionPlanRepository = subscriptionPlanRepository;
    }

    public void execute(BasePrice basePrice, Period period) {
        final SubscriptionPlanId subscriptionPlanId = subscriptionPlanRepository.next();

        final SubscriptionPlan subscriptionPlan = new SubscriptionPlan(subscriptionPlanId, basePrice, period);

        subscriptionPlanRepository.save(subscriptionPlan);
    }
}

package com.gym.use_case;

import com.gym.domain.subscription.SubscriptionPlanRepository;
import com.gym.domain.subscription.BasePrice;
import com.gym.domain.subscription.Period;
import com.gym.domain.subscription.SubscriptionPlan;

public class CreateSubscriptionPlan {
    private final SubscriptionPlanRepository subscriptionPlanRepository;

    public CreateSubscriptionPlan(SubscriptionPlanRepository subscriptionPlanRepository) {
        this.subscriptionPlanRepository = subscriptionPlanRepository;
    }

    public void execute(BasePrice basePrice, Period period) {
        subscriptionPlanRepository.save(new SubscriptionPlan(basePrice, period));
    }
}

package com.gym.use_case;

import com.gym.domain.subscription.SubscriptionPlanIdGenerator;
import com.gym.domain.subscription.*;

public class CreateSubscriptionPlan {
    private final SubscriptionPlanIdGenerator subscriptionPlanIdGenerator;
    private final SubscriptionPlanRepository subscriptionPlanRepository;

    public CreateSubscriptionPlan(SubscriptionPlanIdGenerator subscriptionPlanIdGenerator, SubscriptionPlanRepository subscriptionPlanRepository) {
        this.subscriptionPlanIdGenerator = subscriptionPlanIdGenerator;
        this.subscriptionPlanRepository = subscriptionPlanRepository;
    }

    public void execute(BasePrice basePrice, Period period) {
        final SubscriptionPlanId subscriptionPlanId = subscriptionPlanIdGenerator.next();
        subscriptionPlanRepository.save(new SubscriptionPlan(subscriptionPlanId, basePrice, period));
    }
}

package com.gym.subscription_plan.use_case;

import com.gym.subscription_plan.domain.*;

public class CreateSubscriptionPlan {
    private final SubscriptionPlanRepository subscriptionPlanRepository;

    public CreateSubscriptionPlan(SubscriptionPlanRepository subscriptionPlanRepository) {
        this.subscriptionPlanRepository = subscriptionPlanRepository;
    }

    public void handle(CreateSubscriptionPlanCommand createSubscriptionPlanCommand) {
        final SubscriptionPlanId subscriptionPlanId = subscriptionPlanRepository.next();

        SubscriptionPlan subscriptionPlan;
        if (createSubscriptionPlanCommand.period().equals(Period.Yearly)) {
            subscriptionPlan = SubscriptionPlan.createYearly(subscriptionPlanId, createSubscriptionPlanCommand.basePrice());
        } else {
            subscriptionPlan = SubscriptionPlan.createMonthly(subscriptionPlanId, createSubscriptionPlanCommand.basePrice());
        }

        subscriptionPlanRepository.save(subscriptionPlan);
    }
}

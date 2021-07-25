package com.gym.subscription_plan.use_case;

import com.gym.subscription_plan.domain.*;

public class CreateSubscriptionPlan {
    private final SubscriptionPlanRepository subscriptionPlanRepository;

    public CreateSubscriptionPlan(SubscriptionPlanRepository subscriptionPlanRepository) {
        this.subscriptionPlanRepository = subscriptionPlanRepository;
    }

    public void execute(BasePrice basePrice, Period period) {
        final SubscriptionPlanId subscriptionPlanId = subscriptionPlanRepository.next();

        SubscriptionPlan subscriptionPlan;
        if (period.equals(Period.Yearly)) {
            subscriptionPlan = SubscriptionPlan.createYearly(subscriptionPlanId, basePrice);
        } else {
            subscriptionPlan = SubscriptionPlan.createMonthly(subscriptionPlanId, basePrice);
        }

        subscriptionPlanRepository.save(subscriptionPlan);
    }
}

package com.gym.subscription_plan.use_case;

import com.gym.subscription_plan.domain.Period;

public record CreateSubscriptionPlanCommand(double basePrice, Period period) {
}

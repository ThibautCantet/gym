package com.gym.subscription_plan.infrastructure.controller;

import com.gym.subscription_plan.domain.Period;
import com.gym.subscription_plan.use_case.CreateSubscriptionPlanCommand;

public class CreateSubscriptionPlanDto {
    private double basePrice;
    private Period period;

    public CreateSubscriptionPlanDto(double basePrice, String period) {
        this.basePrice = basePrice;
        this.period = Period.valueOf(period);
    }

    CreateSubscriptionPlanCommand convert() {
        return new CreateSubscriptionPlanCommand(basePrice, period);
    }
}

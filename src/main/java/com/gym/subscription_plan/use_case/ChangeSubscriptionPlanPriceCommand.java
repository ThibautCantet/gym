package com.gym.subscription_plan.use_case;

public record ChangeSubscriptionPlanPriceCommand(String subscriptionPlanId, double price) {
}

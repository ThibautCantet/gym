package com.gym;

public class CreateSubscriptionPlan {
    private SubscriptionPlanRepository subscriptionPlanRepository;

    public CreateSubscriptionPlan(SubscriptionPlanRepository subscriptionPlanRepository) {
        this.subscriptionPlanRepository = subscriptionPlanRepository;
    }

    public void execute(BasePrice basePrice, Period period) {
        subscriptionPlanRepository.save(new SubscriptionPlan(basePrice, period));
    }
}

package com.gym.subscription_plan.use_case;

import com.gym.subscription_plan.domain.SubscriptionPlan;
import com.gym.subscription_plan.domain.SubscriptionPlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllSubscriptionPlans {
    private final SubscriptionPlanRepository subscriptionPlanRepository;

    public GetAllSubscriptionPlans(SubscriptionPlanRepository subscriptionPlanRepository) {
        this.subscriptionPlanRepository = subscriptionPlanRepository;
    }

    public List<SubscriptionPlan> handle(GetAllSubscriptionPlansCommand getAllSubscriptionPlansCommand) {
        return subscriptionPlanRepository.findAll();
    }
}

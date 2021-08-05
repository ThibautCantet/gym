package com.gym.subscription_plan.use_case;

import com.gym.subscription_plan.domain.SubscriptionPlan;
import com.gym.subscription_plan.domain.SubscriptionPlanId;
import com.gym.subscription_plan.domain.SubscriptionPlanRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetSubscriptionPlan {
    private final SubscriptionPlanRepository subscriptionPlanRepository;

    public GetSubscriptionPlan(SubscriptionPlanRepository subscriptionPlanRepository) {
        this.subscriptionPlanRepository = subscriptionPlanRepository;
    }

    public Optional<SubscriptionPlan> handle(GetSubscriptionPlanCommand getSubscriptionPlanCommand) {
        return Optional.ofNullable(subscriptionPlanRepository.findById(new SubscriptionPlanId(getSubscriptionPlanCommand.subscriptionId())));
    }
}

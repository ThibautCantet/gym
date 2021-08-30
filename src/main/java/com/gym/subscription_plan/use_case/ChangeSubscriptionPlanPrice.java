package com.gym.subscription_plan.use_case;

import com.gym.subscription_plan.domain.SubscriptionPlan;
import com.gym.subscription_plan.domain.SubscriptionPlanId;
import com.gym.subscription_plan.domain.SubscriptionPlanRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChangeSubscriptionPlanPrice {
    private final SubscriptionPlanRepository subscriptionPlanRepository;

    public ChangeSubscriptionPlanPrice(SubscriptionPlanRepository subscriptionPlanRepository) {
        this.subscriptionPlanRepository = subscriptionPlanRepository;
    }

    public Optional<SubscriptionPlanId> handle(ChangeSubscriptionPlanPriceCommand changeSubscriptionPlanPriceCommand) {
        final SubscriptionPlan subscriptionPlan = subscriptionPlanRepository.findById(new SubscriptionPlanId(changeSubscriptionPlanPriceCommand.subscriptionPlanId()));
        if (subscriptionPlan != null) {
            final SubscriptionPlan updatedSubscriptionPlan = subscriptionPlan.changePrice(changeSubscriptionPlanPriceCommand.price());

            subscriptionPlanRepository.save(updatedSubscriptionPlan);
            return Optional.of(new SubscriptionPlanId(changeSubscriptionPlanPriceCommand.subscriptionPlanId()));
        }
        return Optional.empty();
    }
}

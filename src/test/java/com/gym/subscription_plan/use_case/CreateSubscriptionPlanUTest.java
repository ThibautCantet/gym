package com.gym.subscription_plan.use_case;

import com.gym.subscription_plan.domain.Period;
import com.gym.subscription_plan.domain.SubscriptionPlan;
import com.gym.subscription_plan.domain.SubscriptionPlanId;
import com.gym.subscription_plan.infrastructure.*;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateSubscriptionPlanUTest {

    private final UUID fixedUUID = UUID.randomUUID();
    private final SubscriptionPlanId subscriptionPlanId = new SubscriptionPlanId(fixedUUID);

    @Test
    public void handle_should_save_new_monthly_subscription_plan() {
        final InMemorySubscriptionPlanRepository subscriptionPlanRepository = new InMemorySubscriptionPlanRepository(fixedUUID);
        final CreateSubscriptionPlan createSubscriptionPlan = new CreateSubscriptionPlan(subscriptionPlanRepository);
        final double basePrice = 10d;
        final Period period = Period.Monthly;
        final CreateSubscriptionPlanCommand createSubscriptionPlanCommand = new CreateSubscriptionPlanCommand(basePrice, period);

        createSubscriptionPlan.handle(createSubscriptionPlanCommand);

        assertThat(subscriptionPlanRepository.getAllSubscriptionPlan())
                .usingFieldByFieldElementComparator()
                .containsExactly(SubscriptionPlan.createMonthly(subscriptionPlanId, basePrice));
    }

    @Test
    public void handle_should_apply_10_percent_discount_and_save_new_yearly_subscription_plan() {
        final InMemorySubscriptionPlanRepository subscriptionPlanRepository = new InMemorySubscriptionPlanRepository(fixedUUID);
        final CreateSubscriptionPlan createSubscriptionPlan = new CreateSubscriptionPlan(subscriptionPlanRepository);
        final double basePrice = 10d;
        final Period period = Period.Yearly;
        final CreateSubscriptionPlanCommand createSubscriptionPlanCommand = new CreateSubscriptionPlanCommand(basePrice, period);

        createSubscriptionPlan.handle(createSubscriptionPlanCommand);

        assertThat(subscriptionPlanRepository.getAllSubscriptionPlan())
                .usingFieldByFieldElementComparator()
                .containsExactly(SubscriptionPlan.createYearly(subscriptionPlanId, basePrice));
    }
}
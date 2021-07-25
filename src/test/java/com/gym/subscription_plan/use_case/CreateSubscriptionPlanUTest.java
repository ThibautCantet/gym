package com.gym.subscription_plan.use_case;

import com.gym.subscription_plan.domain.BasePrice;
import com.gym.subscription_plan.domain.Period;
import com.gym.subscription_plan.domain.SubscriptionPlan;
import com.gym.subscription_plan.domain.SubscriptionPlanId;
import com.gym.subscription_plan.infrastructure.*;
import org.junit.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateSubscriptionPlanUTest {

    private final UUID fixedUUID = UUID.randomUUID();
    private final SubscriptionPlanId subscriptionPlanId = new SubscriptionPlanId(fixedUUID);

    @Test
    public void execute_should_save_new_monthly_subscription_plan() {
        final InMemorySubscriptionPlanRepository subscriptionPlanRepository = new InMemorySubscriptionPlanRepository(fixedUUID);
        final CreateSubscriptionPlan createSubscriptionPlan = new CreateSubscriptionPlan(subscriptionPlanRepository);
        final double basePriceValue = 10d;
        final BasePrice basePrice = new BasePrice(basePriceValue);
        final Period period = Period.Montly;

        createSubscriptionPlan.execute(basePrice, period);

        assertThat(subscriptionPlanRepository.getAllSubscriptionPlan())
                .usingFieldByFieldElementComparator()
                .containsExactly(SubscriptionPlan.createMonthly(subscriptionPlanId, basePrice));
    }

    @Test
    public void execute_should_apply_10_percent_discount_and_save_new_yearly_subscription_plan() {
        final InMemorySubscriptionPlanRepository subscriptionPlanRepository = new InMemorySubscriptionPlanRepository(fixedUUID);
        final CreateSubscriptionPlan createSubscriptionPlan = new CreateSubscriptionPlan(subscriptionPlanRepository);
        final BasePrice basePrice = new BasePrice(10d);
        final Period period = Period.Yearly;

        createSubscriptionPlan.execute(basePrice, period);

        assertThat(subscriptionPlanRepository.getAllSubscriptionPlan())
                .usingFieldByFieldElementComparator()
                .containsExactly(SubscriptionPlan.createYearly(subscriptionPlanId, basePrice));
    }
}
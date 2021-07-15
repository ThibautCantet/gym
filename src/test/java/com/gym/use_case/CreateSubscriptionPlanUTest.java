package com.gym.use_case;

import com.gym.domain.subscription.*;
import com.gym.infrastructure.InMemorySubscriptionPlanRepository;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateSubscriptionPlanUTest {

    @Test
    public void execute_should_save_new_monthly_subscription_plan() {
        final InMemorySubscriptionPlanRepository subscriptionPlanRepository = new InMemorySubscriptionPlanRepository();
        final CreateSubscriptionPlan createSubscriptionPlan = new CreateSubscriptionPlan(subscriptionPlanRepository);
        final double basePriceValue = 10d;
        final BasePrice basePrice = new BasePrice(basePriceValue);
        final Period period = Period.Montly;

        createSubscriptionPlan.execute(basePrice, period);

        assertThat(subscriptionPlanRepository.getAllSubscriptionPlan())
                .usingFieldByFieldElementComparator()
                .containsExactly(new SubscriptionPlan(basePrice, period));
    }

    @Test
    public void execute_should_apply_10_percent_discount_and_save_new_yearly_subscription_plan() {
        final InMemorySubscriptionPlanRepository subscriptionPlanRepository = new InMemorySubscriptionPlanRepository();
        final CreateSubscriptionPlan createSubscriptionPlan = new CreateSubscriptionPlan(subscriptionPlanRepository);
        final BasePrice basePrice = new BasePrice(10d);
        final Period period = Period.Yearly;

        createSubscriptionPlan.execute(basePrice, period);

        assertThat(subscriptionPlanRepository.getAllSubscriptionPlan())
                .usingFieldByFieldElementComparator()
                .containsExactly(new SubscriptionPlan(basePrice, period));
    }
}
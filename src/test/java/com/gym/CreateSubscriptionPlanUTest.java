package com.gym;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateSubscriptionPlanUTest {

    @Test
    public void execute_should_save_new_subscription_plan() {
        final InMemorySubscriptionPlanRepository subscriptionPlanRepository = new InMemorySubscriptionPlanRepository();
        final CreateSubscriptionPlan createSubscriptionPlan = new CreateSubscriptionPlan(subscriptionPlanRepository);
        final BasePrice basePrice = new BasePrice();
        final Period period = Period.Montly;

        createSubscriptionPlan.execute(basePrice, period);

        assertThat(subscriptionPlanRepository.getAllSubscriptionPlan())
                .usingFieldByFieldElementComparator()
                .containsExactly(new SubscriptionPlan(basePrice, period));
        assertThat(subscriptionPlanRepository.getAllSubscriptionPlan().get(0).getBasePrice()).isEqualTo(basePrice);
        assertThat(subscriptionPlanRepository.getAllSubscriptionPlan().get(0).getPeriod()).isEqualTo(period);
    }
}
package com.gym.use_case;

import com.gym.domain.subscription.BasePrice;
import com.gym.domain.subscription.Period;
import com.gym.domain.subscription.SubscriptionPlan;
import com.gym.infrastructure.InMemorySubscriptionPlanRepository;
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
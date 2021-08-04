package com.gym.use_case;

import com.gym.domain.subscription_plan.*;
import com.gym.infrastructure.InMemorySubscriptionPlanRepository;
import org.junit.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class ChangeSubscriptionPlanPriceUTest {
    @Test
    public void execute_should_change_subscription_plan_place() {
        SubscriptionPlanRepository subscriptionPlanRepository = new InMemorySubscriptionPlanRepository(UUID.randomUUID());
        final UUID id = subscriptionPlanRepository.next();
        ((InMemorySubscriptionPlanRepository) subscriptionPlanRepository).subscriptionPlans.put(id, new SubscriptionPlan(id, new BasePrice(100d), Period.Monthly));
        final ChangeSubscriptionPlanPrice changeSubscriptionPlanPrice = new ChangeSubscriptionPlanPrice(subscriptionPlanRepository);
        final double newPrice = 40d;

        changeSubscriptionPlanPrice.execute(id, newPrice);

        assertThat(((InMemorySubscriptionPlanRepository) subscriptionPlanRepository).getAllSubscriptionPlan().get(0).getTotalPrice()).isEqualTo(new TotalPrice(40d));
    }
}

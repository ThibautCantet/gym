package com.gym.use_case;

import com.gym.domain.subscription.*;
import com.gym.infrastructure.InMemorySubscriptionPlanRepository;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class ChangeSubscriptionPlanPriceUTest {

    @Test
    void execute_should_change_subscriptionPlan_price_when_monthly_plan() {
        final SubscriptionPlanRepository subscriptionPlanRepository = new InMemorySubscriptionPlanRepository(UUID.randomUUID());
        final SubscriptionPlanId subscriptionPlanId = new SubscriptionPlanId(UUID.randomUUID());
        ((InMemorySubscriptionPlanRepository) subscriptionPlanRepository).add(new SubscriptionPlan(subscriptionPlanId, new BasePrice(90d), Period.Montly));
        final ChangeSubscriptionPlanPrice changeSubscriptionPlanPrice = new ChangeSubscriptionPlanPrice(subscriptionPlanRepository);

        changeSubscriptionPlanPrice.execute(subscriptionPlanId, new BasePrice(100d));

        final SubscriptionPlan subscriptionPlan = ((InMemorySubscriptionPlanRepository) subscriptionPlanRepository).getAllSubscriptionPlan().get(0);
        assertThat(subscriptionPlan.getBasePrice()).isEqualTo(new BasePrice(100d));
        assertThat(subscriptionPlan.getTotalPrice()).isEqualTo(new TotalPrice(100d));
    }

    @Test
    void execute_should_change_subscriptionPlan_price_when_yearly_plan() {
        final SubscriptionPlanRepository subscriptionPlanRepository = new InMemorySubscriptionPlanRepository(UUID.randomUUID());
        final SubscriptionPlanId subscriptionPlanId = new SubscriptionPlanId(UUID.randomUUID());
        ((InMemorySubscriptionPlanRepository) subscriptionPlanRepository).add(new SubscriptionPlan(subscriptionPlanId, new BasePrice(20d), Period.Yearly));
        final ChangeSubscriptionPlanPrice changeSubscriptionPlanPrice = new ChangeSubscriptionPlanPrice(subscriptionPlanRepository);

        changeSubscriptionPlanPrice.execute(subscriptionPlanId, new BasePrice(100d));

        final SubscriptionPlan subscriptionPlan = ((InMemorySubscriptionPlanRepository) subscriptionPlanRepository).getAllSubscriptionPlan().get(0);
        assertThat(subscriptionPlan.getBasePrice()).isEqualTo(new BasePrice(100d));
        assertThat(subscriptionPlan.getTotalPrice()).isEqualTo(new TotalPrice(90d));
    }
}
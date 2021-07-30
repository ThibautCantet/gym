package com.gym.subscription_plan.use_case;

import com.gym.subscription_plan.domain.*;
import com.gym.subscription_plan.infrastructure.*;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class ChangeSubscriptionPlanPriceUTest {

    @Test
    void handle_should_change_subscriptionPlan_price_when_monthly_plan() {
        final SubscriptionPlanRepository subscriptionPlanRepository = new InMemorySubscriptionPlanRepository(UUID.randomUUID());
        final String subscriptionPlanId = UUID.randomUUID().toString();
        ((InMemorySubscriptionPlanRepository) subscriptionPlanRepository).add(SubscriptionPlan.createMonthly(new SubscriptionPlanId(subscriptionPlanId), 90d));
        final ChangeSubscriptionPlanPrice changeSubscriptionPlanPrice = new ChangeSubscriptionPlanPrice(subscriptionPlanRepository);
        final ChangeSubscriptionPlanPriceCommand changeSubscriptionPlanPriceCommand = new ChangeSubscriptionPlanPriceCommand(subscriptionPlanId, 100d);

        changeSubscriptionPlanPrice.handle(changeSubscriptionPlanPriceCommand);

        final SubscriptionPlan subscriptionPlan = ((InMemorySubscriptionPlanRepository) subscriptionPlanRepository).getAllSubscriptionPlan().get(0);
        assertThat(subscriptionPlan.getBasePrice()).isEqualTo(new BasePrice(100d));
        assertThat(subscriptionPlan.getTotalPrice()).isEqualTo(new TotalPrice(100d));
    }

    @Test
    void handle_should_change_subscriptionPlan_price_when_yearly_plan() {
        final SubscriptionPlanRepository subscriptionPlanRepository = new InMemorySubscriptionPlanRepository(UUID.randomUUID());
        final String subscriptionPlanId = UUID.randomUUID().toString();
        ((InMemorySubscriptionPlanRepository) subscriptionPlanRepository).add(SubscriptionPlan.createYearly(new SubscriptionPlanId(subscriptionPlanId), 20d));
        final ChangeSubscriptionPlanPrice changeSubscriptionPlanPrice = new ChangeSubscriptionPlanPrice(subscriptionPlanRepository);
        final ChangeSubscriptionPlanPriceCommand changeSubscriptionPlanPriceCommand = new ChangeSubscriptionPlanPriceCommand(subscriptionPlanId, 100d);

        changeSubscriptionPlanPrice.handle(changeSubscriptionPlanPriceCommand);

        final SubscriptionPlan subscriptionPlan = ((InMemorySubscriptionPlanRepository) subscriptionPlanRepository).getAllSubscriptionPlan().get(0);
        assertThat(subscriptionPlan.getBasePrice()).isEqualTo(new BasePrice(100d));
        assertThat(subscriptionPlan.getTotalPrice()).isEqualTo(new TotalPrice(90d));
    }
}
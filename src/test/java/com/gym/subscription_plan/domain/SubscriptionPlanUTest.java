package com.gym.subscription_plan.domain;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class SubscriptionPlanUTest {

    private final SubscriptionPlanId subscriptionPlanId = new SubscriptionPlanId(UUID.randomUUID());

    @Nested
    class ConstructorShould {
        @Test
        void build_subscriptionPlan_without_discount_when_period_is_monthly() {
            final double basePriceValue = 10d;
            final BasePrice basePrice = new BasePrice(basePriceValue);

            final SubscriptionPlan subscriptionPlan = SubscriptionPlan.createMonthly(subscriptionPlanId, basePriceValue);

            assertThat(subscriptionPlan.getId()).isEqualTo(subscriptionPlanId);
            assertThat(subscriptionPlan.getBasePrice()).isEqualTo(basePrice);
            assertThat(subscriptionPlan.getTotalPrice()).isEqualTo(new TotalPrice(basePriceValue));
            assertThat(subscriptionPlan.getPeriod()).isEqualTo(Period.Monthly);
            assertThat(subscriptionPlan.getDiscountRate()).isEqualTo(new DiscountRate(0d));
        }

        @Test
        void build_subscriptionPlan_without_discount_when_period_is_yearly() {
            final double basePriceValue = 10d;
            final BasePrice basePrice = new BasePrice(basePriceValue);

            final SubscriptionPlan subscriptionPlan = SubscriptionPlan.createYearly(subscriptionPlanId, basePriceValue);

            assertThat(subscriptionPlan.getId()).isEqualTo(subscriptionPlanId);
            assertThat(subscriptionPlan.getBasePrice()).isEqualTo(basePrice);
            assertThat(subscriptionPlan.getTotalPrice()).isEqualTo(new TotalPrice(9d));
            assertThat(subscriptionPlan.getPeriod()).isEqualTo(Period.Yearly);
            assertThat(subscriptionPlan.getDiscountRate()).isEqualTo(new DiscountRate(10d));
        }
    }
}
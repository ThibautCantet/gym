package com.gym.use_case;

import com.gym.domain.subscriber.*;
import com.gym.domain.subscription.*;
import com.gym.infrastructure.InMemorySubscriptionPlanRepository;
import com.gym.infrastructure.InMemorySubscriptionRepository;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class RenewMonthlySubscriptionUTest {

    private static final Clock LAST_MONTH = Clock.fixed(Instant.parse("2021-06-21T12:00:00.00Z"), ZoneId.systemDefault());
    private static final Clock NOW = Clock.fixed(Instant.parse("2021-07-21T12:00:00.00Z"), ZoneId.systemDefault());

    @Test
    void execute_should_automatically_renew_monthly_subscription() {
        final SubscriptionPlanId monthlySubscriptionPlanId = new SubscriptionPlanId(UUID.randomUUID());
        final SubscriptionPlan monthlySubscriptionPlan = SubscriptionPlan.createMonthly(monthlySubscriptionPlanId, new BasePrice(10d));
        final SubscriptionPlanRepository subscriptionPlanRepository = new InMemorySubscriptionPlanRepository(UUID.randomUUID());
        ((InMemorySubscriptionPlanRepository) subscriptionPlanRepository).add(monthlySubscriptionPlan);

        final SubscriptionId monthlySubscriptionId = new SubscriptionId(UUID.randomUUID());
        final SubscriptionRepository subscriptionRepository = new InMemorySubscriptionRepository(UUID.randomUUID());
        final Subscription monthlySubscriptionToRenew = Subscription.subscribe(
                monthlySubscriptionId,
                monthlySubscriptionPlanId,
                Period.Montly,
                new TotalPrice(80d),
                Subscriber.createRegular(new SubscriberId(UUID.randomUUID())),
                LAST_MONTH);
        ((InMemorySubscriptionRepository) subscriptionRepository).addSubscriptions(Collections.singletonList(monthlySubscriptionToRenew));

        final RenewMonthlySubscription renewMonthlySubscription = new RenewMonthlySubscription(subscriptionRepository, NOW);

        renewMonthlySubscription.execute();

        final LocalDate today = LocalDate.now(NOW);
        final LocalDate lastMonth = LocalDate.now(LAST_MONTH);
        final LocalDate inOneMonth = today.plusMonths(1);
        final Subscription updatedMonthlySubscription = ((InMemorySubscriptionRepository) subscriptionRepository).findById(monthlySubscriptionId);
        assertThat(updatedMonthlySubscription.getSubscriptionDate()).isEqualTo(new SubscriptionDate(lastMonth, inOneMonth));
    }

    @Test
    void execute_should_not_automatically_renew_yearly_subscription() {
        final SubscriptionPlanId yearlySubscriptionPlanId = new SubscriptionPlanId(UUID.randomUUID());
        final SubscriptionPlan yearlySubscriptionPlan = SubscriptionPlan.createYearly(yearlySubscriptionPlanId, new BasePrice(100d));
        final SubscriptionPlanRepository subscriptionPlanRepository = new InMemorySubscriptionPlanRepository(UUID.randomUUID());
        ((InMemorySubscriptionPlanRepository) subscriptionPlanRepository).add(yearlySubscriptionPlan);

        final SubscriptionId yearlySubscriptionId = new SubscriptionId(UUID.randomUUID());
        final Subscription yearlySubscription = Subscription.subscribe(
                yearlySubscriptionId,
                yearlySubscriptionPlanId,
                Period.Yearly,
                new TotalPrice(100d),
                Subscriber.createRegular(new SubscriberId(UUID.randomUUID())),
                LAST_MONTH);
        final SubscriptionRepository subscriptionRepository = new InMemorySubscriptionRepository(UUID.randomUUID());
        ((InMemorySubscriptionRepository) subscriptionRepository).addSubscriptions(Collections.singletonList(yearlySubscription));

        final RenewMonthlySubscription renewMonthlySubscription = new RenewMonthlySubscription(subscriptionRepository, NOW);

        renewMonthlySubscription.execute();

        final LocalDate lastMonth = LocalDate.now(LAST_MONTH);
        final LocalDate inOneYear = lastMonth.plusYears(1);
        final Subscription notUpdatedYearlySubscription = ((InMemorySubscriptionRepository) subscriptionRepository).findById(yearlySubscriptionId);
        assertThat(notUpdatedYearlySubscription.getSubscriptionDate()).isEqualTo(new SubscriptionDate(lastMonth, inOneYear));
    }
}
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
import java.util.UUID;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class RenewMonthlySubscriptionUTest {

    private static final Clock LAST_MONTH = Clock.fixed(Instant.parse("2021-06-21T12:00:00.00Z"), ZoneId.systemDefault());
    private static final Clock NOW = Clock.fixed(Instant.parse("2021-07-21T12:00:00.00Z"), ZoneId.systemDefault());

    @Test
    void execute_should_automatically_renew_subscription() {
        final SubscriptionPlanId monthlySubscriptionPlanId = new SubscriptionPlanId(UUID.randomUUID());

        final Subscription monthlySubscriptionToRenew = Subscription.subscribe(
                new SubscriptionId(UUID.randomUUID()),
                monthlySubscriptionPlanId,
                Period.Montly,
                new TotalPrice(80d),
                Subscriber.createRegular(new SubscriberId(UUID.randomUUID())),
                LAST_MONTH);

        final SubscriptionPlanId yearlySubscriptionPlanId = new SubscriptionPlanId(UUID.randomUUID());

        final Subscription yearlySubscription = Subscription.subscribe(
                new SubscriptionId(UUID.randomUUID()),
                yearlySubscriptionPlanId,
                Period.Yearly,
                new TotalPrice(100d),
                Subscriber.createRegular(new SubscriberId(UUID.randomUUID())),
                LAST_MONTH);

        final SubscriptionPlan monthlySubscriptionPlan = SubscriptionPlan.createMonthly(monthlySubscriptionPlanId, new BasePrice(10d));
        final SubscriptionPlan yearlySubscriptionPlan = SubscriptionPlan.createYearly(yearlySubscriptionPlanId, new BasePrice(100d));
        final SubscriptionPlanRepository subscriptionPlanRepository = new InMemorySubscriptionPlanRepository(UUID.randomUUID());
        ((InMemorySubscriptionPlanRepository)subscriptionPlanRepository).add(monthlySubscriptionPlan);
        ((InMemorySubscriptionPlanRepository)subscriptionPlanRepository).add(yearlySubscriptionPlan);

        final SubscriptionRepository subscriptionRepository = new InMemorySubscriptionRepository(UUID.randomUUID());
        ((InMemorySubscriptionRepository) subscriptionRepository).addSubscriptions(
                asList(
                        monthlySubscriptionToRenew,
                        yearlySubscription
                        )
        );

        final RenewMonthlySubscription renewMonthlySubscription = new RenewMonthlySubscription(subscriptionRepository, NOW);

        renewMonthlySubscription.execute();

        final LocalDate today = LocalDate.now(NOW);
        final LocalDate lastMonth = LocalDate.now(LAST_MONTH);
        final LocalDate inOneMonth = today.plusMonths(1);
        assertThat(monthlySubscriptionToRenew.getSubscriptionDate()).isEqualTo(new SubscriptionDate(lastMonth, inOneMonth));
        assertThat(yearlySubscription.getSubscriptionDate()).isEqualTo(new SubscriptionDate(lastMonth, lastMonth.plusYears(1)));
    }
}
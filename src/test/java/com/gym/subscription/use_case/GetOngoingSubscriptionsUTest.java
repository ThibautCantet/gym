package com.gym.subscription.use_case;

import com.gym.subscription.domain.Subscription;
import com.gym.subscription.domain.SubscriptionId;
import com.gym.subscription.domain.SubscriptionRepository;
import com.gym.subscription.infrastructure.InMemorySubscriptionRepository;
import com.gym.subscription_plan.domain.Period;
import com.gym.subscription_plan.domain.TotalPrice;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.UUID;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class GetOngoingSubscriptionsUTest {

    private static final Clock TWO_MONTH_AGO = Clock.fixed(Instant.parse("2021-01-10T16:00:00.00Z"), ZoneId.systemDefault());
    private static final Clock LAST_MONTH = Clock.fixed(Instant.parse("2021-02-19T16:00:00.00Z"), ZoneId.systemDefault());
    private static final Clock NOW = Clock.fixed(Instant.parse("2021-03-09T16:00:00.00Z"), ZoneId.systemDefault());
    private static final Period MONTHLY_PERIOD = Period.Monthly;

    @Test
    void handle_should_return_all_ongoing_subscriptions() {
        final SubscriptionRepository subscriptionRepository = new InMemorySubscriptionRepository(UUID.randomUUID());
        ((InMemorySubscriptionRepository) subscriptionRepository).addSubscriptions(
                asList(
                        Subscription.subscribe(
                                new SubscriptionId(UUID.randomUUID()),
                                MONTHLY_PERIOD,
                                new TotalPrice(80d),
                                false,
                                TWO_MONTH_AGO),
                        Subscription.subscribe(
                                new SubscriptionId(UUID.randomUUID()),
                                MONTHLY_PERIOD,
                                new TotalPrice(100d),
                                false,
                                LAST_MONTH),
                        Subscription.subscribe(
                                new SubscriptionId(UUID.randomUUID()),
                                MONTHLY_PERIOD,
                                new TotalPrice(100d),
                                false,
                                LAST_MONTH),
                        Subscription.subscribe(
                                new SubscriptionId(UUID.randomUUID()),
                                MONTHLY_PERIOD,
                                new TotalPrice(110d),
                                false,
                                NOW))
        );

        final GetOngoingSubscriptions getOngoingSubscriptions = new GetOngoingSubscriptions(subscriptionRepository);
        final GetOngoingSubscriptionsCommand getOngoingSubscriptionsCommand = new GetOngoingSubscriptionsCommand(LocalDate.now(LAST_MONTH));

        Double result = getOngoingSubscriptions.handle(getOngoingSubscriptionsCommand);

        assertThat(result).isEqualTo(280d);
    }
}
package com.gym.use_case;

import com.gym.membership.domain.Email;
import com.gym.membership.domain.Member;
import com.gym.membership.domain.MemberId;
import com.gym.domain.subscription.*;
import com.gym.domain.subscription_plan.Period;
import com.gym.domain.subscription_plan.SubscriptionPlanId;
import com.gym.domain.subscription_plan.TotalPrice;
import com.gym.infrastructure.InMemorySubscriptionRepository;
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
    private static final Period MONTHLY_PERIOD = Period.Montly;
    private final Email email = new Email("test@email.com");

    @Test
    void execute_should_return_all_ongoing_subscriptions() {
        final SubscriptionPlanId subscriptionPlanId = new SubscriptionPlanId(UUID.randomUUID());

        final SubscriptionRepository subscriptionRepository = new InMemorySubscriptionRepository(UUID.randomUUID());
        ((InMemorySubscriptionRepository) subscriptionRepository).addSubscriptions(
                asList(
                        Subscription.subscribe(
                                new SubscriptionId(UUID.randomUUID()),
                                subscriptionPlanId,
                                MONTHLY_PERIOD,
                                new TotalPrice(80d),
                                Member.createRegular(new MemberId(UUID.randomUUID()), email),
                                TWO_MONTH_AGO),
                        Subscription.subscribe(
                                new SubscriptionId(UUID.randomUUID()),
                                subscriptionPlanId,
                                MONTHLY_PERIOD,
                                new TotalPrice(100d),
                                Member.createRegular(new MemberId(UUID.randomUUID()), email),
                                LAST_MONTH),
                        Subscription.subscribe(
                                new SubscriptionId(UUID.randomUUID()),
                                subscriptionPlanId,
                                MONTHLY_PERIOD,
                                new TotalPrice(100d),
                                Member.createRegular(new MemberId(UUID.randomUUID()), email),
                                LAST_MONTH),
                        Subscription.subscribe(
                                new SubscriptionId(UUID.randomUUID()),
                                subscriptionPlanId,
                                MONTHLY_PERIOD,
                                new TotalPrice(110d),
                                Member.createRegular(new MemberId(UUID.randomUUID()), email),
                                NOW))
        );

        final GetOngoingSubscriptions getOngoingSubscriptions = new GetOngoingSubscriptions(subscriptionRepository);

        Double result = getOngoingSubscriptions.execute(LocalDate.now(LAST_MONTH));

        assertThat(result).isEqualTo(280d);
    }
}
package com.gym.subscription.use_case;

import com.gym.membership.domain.Email;
import com.gym.membership.domain.Member;
import com.gym.membership.domain.MemberId;
import com.gym.subscription.domain.Price;
import com.gym.subscription.domain.Subscription;
import com.gym.subscription.domain.SubscriptionId;
import com.gym.subscription.domain.SubscriptionRepository;
import com.gym.subscription.infrastructure.InMemorySubscriptionRepository;
import com.gym.subscription_plan.domain.Period;
import com.gym.subscription_plan.domain.SubscriptionPlanId;
import com.gym.subscription_plan.domain.TotalPrice;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.UUID;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class ApplyThirdAnniversaryDiscountUTest {

    private static final Clock THREE_YEARS_AGO = Clock.fixed(Instant.parse("2018-07-30T16:00:00.00Z"), ZoneId.systemDefault());
    private static final Clock LAST_MONTH = Clock.fixed(Instant.parse("2021-06-30T16:00:00.00Z"), ZoneId.systemDefault());
    private static final Clock NOW = Clock.fixed(Instant.parse("2021-07-30T16:00:00.00Z"), ZoneId.systemDefault());

    @Test
    void execute_should_apply_discount_for_all_subscription_at_their_third_anniversary() {
        final SubscriptionRepository subscriptionRepository = new InMemorySubscriptionRepository(UUID.randomUUID());
        final SubscriptionPlanId subscriptionPlanId = new SubscriptionPlanId(UUID.randomUUID());
        final SubscriptionId threeYearSubscriptionId = new SubscriptionId(UUID.randomUUID());
        final SubscriptionId otherSubscriptionId = new SubscriptionId(UUID.randomUUID());
        ((InMemorySubscriptionRepository) subscriptionRepository).addSubscriptions(
                asList(
                        Subscription.subscribe(
                                threeYearSubscriptionId,
                                subscriptionPlanId,
                                Period.Monthly,
                                new TotalPrice(100d),
                                Member.createRegular(new MemberId(UUID.randomUUID()), new Email("one@kata.com")),
                                THREE_YEARS_AGO),
                        Subscription.subscribe(
                                otherSubscriptionId,
                                subscriptionPlanId,
                                Period.Yearly,
                                new TotalPrice(80d),
                                Member.createRegular(new MemberId(UUID.randomUUID()), new Email("one@kata.com")),
                                LAST_MONTH)
                        )
        );
        final ApplyThirdAnniversaryDiscount applyThirdAnniversaryDiscount = new ApplyThirdAnniversaryDiscount(subscriptionRepository, NOW);

        applyThirdAnniversaryDiscount.execute();

        final Subscription updatedSubscriptionWithDiscount = ((InMemorySubscriptionRepository) subscriptionRepository).findById(threeYearSubscriptionId);
        assertThat(updatedSubscriptionWithDiscount.getPrice()).isEqualTo(new Price(95d));

        final Subscription otherSubscriptionWithoutDiscount = ((InMemorySubscriptionRepository) subscriptionRepository).findById(otherSubscriptionId);
        assertThat(otherSubscriptionWithoutDiscount.getPrice()).isEqualTo(new Price(80d));
    }
}
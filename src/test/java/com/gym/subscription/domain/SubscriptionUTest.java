package com.gym.subscription.domain;

import com.gym.subscription_plan.domain.Period;
import com.gym.subscription_plan.domain.TotalPrice;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class SubscriptionUTest {
    private static final Clock clock = Clock.fixed(Instant.ofEpochSecond(3600), ZoneId.systemDefault());
    private static final Period MONTHLY_PERIOD = Period.Monthly;
    private static final Period YEARLY_PERIOD = Period.Yearly;
    private final SubscriptionId subscriptionId = new SubscriptionId(UUID.randomUUID());
    public static final LocalDate TODAY = LocalDate.now(clock);
    public static final LocalDate IN_ONE_MONTH = TODAY.plusMonths(1);
    public static final LocalDate IN_ONE_YEAR = TODAY.plusYears(1);

    @Nested
    class SubscribeShould {
        @Test
        void build_subscription_with_price_for_regular_subscriber() {
            final TotalPrice totalPrice = new TotalPrice(10d);

            final Subscription subscription = Subscription.subscribe(subscriptionId, MONTHLY_PERIOD, totalPrice, false, clock);

            assertThat(subscription.getSubscriptionId()).isEqualTo(subscriptionId);
            assertThat(subscription.getPrice()).isEqualTo(new Price(10d));
        }

        @Test
        void build_subscription_with_discounted_price_for_student_subscriber() {
            final TotalPrice totalPrice = new TotalPrice(100d);

            final Subscription subscription = Subscription.subscribe(subscriptionId, MONTHLY_PERIOD, totalPrice, true, clock);

            assertThat(subscription.getSubscriptionId()).isEqualTo(subscriptionId);
            assertThat(subscription.getPrice()).isEqualTo(new Price(80d));
        }

        @Test
        void build_monthly_subscription_for_one_month() {
            final TotalPrice totalPrice = new TotalPrice(10d);

            final Subscription subscription = Subscription.subscribe(subscriptionId, MONTHLY_PERIOD, totalPrice, false, clock);

            assertThat(subscription.getSubscriptionDate()).isEqualTo(new SubscriptionDate(TODAY, IN_ONE_MONTH));
        }
        @Test
        void build_yearly_subscription_for_one_year() {
            final TotalPrice totalPrice = new TotalPrice(10d);

            final Subscription subscription = Subscription.subscribe(subscriptionId, YEARLY_PERIOD, totalPrice, false, clock);

            assertThat(subscription.getSubscriptionDate()).isEqualTo(new SubscriptionDate(TODAY, IN_ONE_YEAR));
        }
    }
}
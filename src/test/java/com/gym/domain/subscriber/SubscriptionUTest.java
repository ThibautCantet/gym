package com.gym.domain.subscriber;

import com.gym.domain.subscription.SubscriptionPlanId;
import com.gym.domain.subscription.TotalPrice;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class SubscriptionUTest {
    private static final Clock clock = Clock.fixed(Instant.ofEpochSecond(3600), ZoneId.systemDefault());
    private final SubscriptionId subscriptionId = new SubscriptionId(UUID.randomUUID());

    @Nested
    class SubscribeShould {
        @Test
        void build_subscription_with_price_for_regular_subscriber() {
            final SubscriptionPlanId subscriptionPlanId = new SubscriptionPlanId(UUID.randomUUID());
            final TotalPrice totalPrice = new TotalPrice(10d);
            final SubscriberId subscriberId = new SubscriberId(UUID.randomUUID());
            final Subscriber subscriber = Subscriber.createRegular(subscriberId);

            final Subscription subscription = Subscription.subscribe(subscriptionId, subscriptionPlanId, totalPrice, subscriber, clock);

            assertThat(subscription.getSubscriptionId()).isEqualTo(subscriptionId);
            assertThat(subscription.getSubscriberId()).isEqualTo(subscriberId);
            assertThat(subscription.getSubscriptionPlanId()).isEqualTo(subscriptionPlanId);
            assertThat(subscription.getSubscriptionDate()).isEqualTo(new SubscriptionDate(LocalDateTime.now(clock)));
            assertThat(subscription.getPrice()).isEqualTo(new Price(10d));
        }

        @Test
        void build_subscription_with_discounted_price_for_student_subscriber() {
            final SubscriptionPlanId subscriptionPlanId = new SubscriptionPlanId(UUID.randomUUID());
            final TotalPrice totalPrice = new TotalPrice(100d);
            final SubscriberId subscriberId = new SubscriberId(UUID.randomUUID());
            final Subscriber subscriber = Subscriber.createStudent(subscriberId);

            final Subscription subscription = Subscription.subscribe(subscriptionId, subscriptionPlanId, totalPrice, subscriber, clock);

            assertThat(subscription.getSubscriptionId()).isEqualTo(subscriptionId);
            assertThat(subscription.getSubscriberId()).isEqualTo(subscriberId);
            assertThat(subscription.getSubscriptionPlanId()).isEqualTo(subscriptionPlanId);
            assertThat(subscription.getSubscriptionDate()).isEqualTo(new SubscriptionDate(LocalDateTime.now(clock)));
            assertThat(subscription.getPrice()).isEqualTo(new Price(80d));
        }
    }
}
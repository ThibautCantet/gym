package com.gym.domain.subscriber;

import com.gym.domain.subscription.BasePrice;
import com.gym.domain.subscription.SubscriptionPlanId;
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
    class ConstructorShould {
        @Test
        void build_subscription_with_base_price() {
            final SubscriptionPlanId subscriptionPlanId = new SubscriptionPlanId(UUID.randomUUID());
            final BasePrice basePrice = new BasePrice(10d);
            final Subscriber subscriber = new Subscriber();

            final Subscription subscription = new Subscription(subscriptionId, subscriptionPlanId, basePrice, subscriber, clock);

            assertThat(subscription.getSubscriptionId()).isEqualTo(subscriptionId);
            assertThat(subscription.getSubscriptionPlanId()).isEqualTo(subscriptionPlanId);
            assertThat(subscription.getSubscriptionDate()).isEqualTo(new SubscriptionDate(LocalDateTime.now(clock)));
            assertThat(subscription.getPrice()).isEqualTo(new Price(10d));
        }
    }
}
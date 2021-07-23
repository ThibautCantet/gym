package com.gym.use_case;

import com.gym.domain.membership.Email;
import com.gym.domain.membership.Subscriber;
import com.gym.domain.membership.SubscriberId;
import com.gym.domain.subscriber.*;
import com.gym.domain.subscription.*;
import com.gym.infrastructure.InMemorySubscriptionPlanRepository;
import com.gym.infrastructure.InMemorySubscriptionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class SubscribeUTest {

    private static final Clock clock = Clock.fixed(Instant.ofEpochSecond(3600), ZoneId.systemDefault());
    private final UUID fixedUUID = UUID.randomUUID();
    private final SubscriptionPlanId subscriptionPlanId = new SubscriptionPlanId(UUID.randomUUID());

    private final SubscriptionPlanRepository subscriptionPlanRepository = new InMemorySubscriptionPlanRepository(fixedUUID);
    private final SubscriptionRepository subscriptionRepository = new InMemorySubscriptionRepository(fixedUUID);

    @BeforeEach
    void setUp() {
        ((InMemorySubscriptionPlanRepository) subscriptionPlanRepository).getAllSubscriptionPlan().add(SubscriptionPlan.createMonthly(subscriptionPlanId, new BasePrice(10d)));
    }

    @Nested
    class ExecuteShould {
        @Test
        void save_new_subscription() {
            final Subscribe subscribe = new Subscribe(subscriptionPlanRepository, subscriptionRepository, clock);
            final Subscriber subscriber = Subscriber.createRegular(new SubscriberId(UUID.randomUUID()), new Email("test@email.com"));

            subscribe.execute(subscriber, subscriptionPlanId);

            assertThat(((InMemorySubscriptionRepository) subscriptionRepository).getSubscriptions())
                    .usingFieldByFieldElementComparator()
                    .containsExactly(Subscription.subscribe(
                            new SubscriptionId(fixedUUID),
                            subscriptionPlanId,
                            Period.Montly,
                            new TotalPrice(10d),
                            subscriber,
                            clock));
        }
    }
}
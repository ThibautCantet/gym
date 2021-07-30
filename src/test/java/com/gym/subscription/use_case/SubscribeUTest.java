package com.gym.subscription.use_case;

import com.gym.membership.domain.MemberRepository;
import com.gym.membership.infrastructure.InMemoryMemberRepository;
import com.gym.subscription.domain.Subscription;
import com.gym.subscription.domain.SubscriptionId;
import com.gym.subscription.domain.SubscriptionRepository;
import com.gym.membership.domain.Member;
import com.gym.membership.domain.MemberId;
import com.gym.subscription_plan.domain.*;
import com.gym.subscription_plan.infrastructure.*;
import com.gym.subscription.infrastructure.InMemorySubscriptionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class SubscribeUTest {

    private static final String MEMBER_ID_VALUE = UUID.randomUUID().toString();
    private static final Clock clock = Clock.fixed(Instant.ofEpochSecond(3600), ZoneId.systemDefault());
    private static final String SUBSCRIPTION_PLAN_ID_VALUE = UUID.randomUUID().toString();
    private final UUID fixedUUID = UUID.randomUUID();
    private final SubscriptionPlanId subscriptionPlanId = new SubscriptionPlanId(SUBSCRIPTION_PLAN_ID_VALUE);

    private final SubscriptionPlanRepository subscriptionPlanRepository = new InMemorySubscriptionPlanRepository(fixedUUID);
    private final SubscriptionRepository subscriptionRepository = new InMemorySubscriptionRepository(fixedUUID);
    private final MemberRepository memberRepository = new InMemoryMemberRepository(fixedUUID);

    @BeforeEach
    void setUp() {
        ((InMemorySubscriptionPlanRepository) subscriptionPlanRepository).getAllSubscriptionPlan().add(SubscriptionPlan.createMonthly(subscriptionPlanId, 10d));
        final MemberId memberId = new MemberId(MEMBER_ID_VALUE);
        final Member regularMember = Member.createRegular(memberId, "test@email.com");
        memberRepository.save(regularMember);
    }

    @Nested
    class HandleShould {
        @Test
        void save_new_subscription() {
            final Subscribe subscribe = new Subscribe(memberRepository, subscriptionPlanRepository, subscriptionRepository, clock);
            final SubscribeCommand subscribeCommand = new SubscribeCommand(MEMBER_ID_VALUE, SUBSCRIPTION_PLAN_ID_VALUE);

            subscribe.handle(subscribeCommand);

            assertThat(((InMemorySubscriptionRepository) subscriptionRepository).getSubscriptions())
                    .usingFieldByFieldElementComparator()
                    .containsExactly(Subscription.subscribe(
                            new SubscriptionId(fixedUUID),
                            Period.Monthly,
                            new TotalPrice(10d),
                            false,
                            clock
                    ));
        }
    }
}
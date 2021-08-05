package com.gym.subscription_plan.use_case;

import com.gym.subscription_plan.domain.SubscriptionPlan;
import com.gym.subscription_plan.domain.SubscriptionPlanId;
import com.gym.subscription_plan.domain.SubscriptionPlanRepository;
import com.gym.subscription_plan.infrastructure.InMemorySubscriptionPlanRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class GetSubscriptionPlanUTest {

    @Test
    void handle_should_return_empty_when_does_not_exist() {
        final UUID fixedUUID = UUID.randomUUID();
        final SubscriptionPlanRepository inMemorySubscriptionPlanRepository = new InMemorySubscriptionPlanRepository(fixedUUID);
        final GetSubscriptionPlan getSubscriptionPlan = new GetSubscriptionPlan(inMemorySubscriptionPlanRepository);
        final GetSubscriptionPlanCommand getSubscriptionPlanCommand = new GetSubscriptionPlanCommand(fixedUUID);

        final Optional<SubscriptionPlan> optionalSubscriptionPlan = getSubscriptionPlan.handle(getSubscriptionPlanCommand);

        assertThat(optionalSubscriptionPlan.isEmpty()).isTrue();
    }

    @Test
    void handle_should_return_searched_subscriptionPlan_when_exists() {
        final UUID fixedUUID = UUID.randomUUID();
        final SubscriptionPlanRepository inMemorySubscriptionPlanRepository = new InMemorySubscriptionPlanRepository(fixedUUID);
        final SubscriptionPlan subscriptionPlan = SubscriptionPlan.createYearly(new SubscriptionPlanId(fixedUUID), 100d);
        ((InMemorySubscriptionPlanRepository) inMemorySubscriptionPlanRepository).add(subscriptionPlan);
        final GetSubscriptionPlan getSubscriptionPlan = new GetSubscriptionPlan(inMemorySubscriptionPlanRepository);
        final GetSubscriptionPlanCommand getSubscriptionPlanCommand = new GetSubscriptionPlanCommand(fixedUUID);

        final Optional<SubscriptionPlan> optionalSubscriptionPlan = getSubscriptionPlan.handle(getSubscriptionPlanCommand);

        assertThat(optionalSubscriptionPlan.get()).isEqualToComparingFieldByField(subscriptionPlan);
    }
}
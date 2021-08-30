package com.gym.subscription_plan.use_case;

import com.gym.subscription_plan.domain.SubscriptionPlan;
import com.gym.subscription_plan.domain.SubscriptionPlanId;
import com.gym.subscription_plan.domain.SubscriptionPlanRepository;
import com.gym.subscription_plan.infrastructure.InMemorySubscriptionPlanRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class GetAllSubscriptionPlansUTest {

    @Test
    void handle_should_return_empty_when_no_exist() {
        final SubscriptionPlanRepository inMemorySubscriptionPlanRepository = new InMemorySubscriptionPlanRepository(UUID.randomUUID());
        final GetAllSubscriptionPlans getSubscriptionPlans = new GetAllSubscriptionPlans(inMemorySubscriptionPlanRepository);
        final GetAllSubscriptionPlansCommand getAllSubscriptionPlansCommand = new GetAllSubscriptionPlansCommand();

        final List<SubscriptionPlan> optionalSubscriptionPlan = getSubscriptionPlans.handle(getAllSubscriptionPlansCommand);

        assertThat(optionalSubscriptionPlan.isEmpty()).isTrue();
    }

    @Test
    void handle_should_return_all_subscriptionPlans() {
        final UUID fixedUUID = UUID.randomUUID();
        final SubscriptionPlanRepository inMemorySubscriptionPlanRepository = new InMemorySubscriptionPlanRepository(fixedUUID);
        final SubscriptionPlan subscriptionPlan = SubscriptionPlan.createYearly(new SubscriptionPlanId(fixedUUID), 100d);
        ((InMemorySubscriptionPlanRepository) inMemorySubscriptionPlanRepository).add(subscriptionPlan);
        final GetAllSubscriptionPlans getSubscriptionPlans = new GetAllSubscriptionPlans(inMemorySubscriptionPlanRepository);
        final GetAllSubscriptionPlansCommand getAllSubscriptionPlansCommand = new GetAllSubscriptionPlansCommand();

        final List<SubscriptionPlan> subscriptionPlans = getSubscriptionPlans.handle(getAllSubscriptionPlansCommand);

        assertThat(subscriptionPlans).containsExactly(subscriptionPlan);
    }
}
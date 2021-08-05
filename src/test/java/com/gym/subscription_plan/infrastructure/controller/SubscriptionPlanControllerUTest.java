package com.gym.subscription_plan.infrastructure.controller;

import com.gym.subscription_plan.domain.Period;
import com.gym.subscription_plan.domain.SubscriptionPlan;
import com.gym.subscription_plan.domain.SubscriptionPlanId;
import com.gym.subscription_plan.domain.SubscriptionPlanRepository;
import com.gym.subscription_plan.infrastructure.InMemorySubscriptionPlanRepository;
import com.gym.subscription_plan.use_case.CreateSubscriptionPlan;
import com.gym.subscription_plan.use_case.CreateSubscriptionPlanCommand;
import com.gym.subscription_plan.use_case.GetSubscriptionPlan;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

class SubscriptionPlanControllerUTest {

    private final UUID expectedUUID = UUID.randomUUID();
    private final SubscriptionPlanRepository subscriptionPlanRepository = new InMemorySubscriptionPlanRepository(expectedUUID);
    private final CreateSubscriptionPlan createSubscriptionPlan = new CreateSubscriptionPlan(subscriptionPlanRepository);
    private final GetSubscriptionPlan getSubscriptionPlan = new GetSubscriptionPlan(subscriptionPlanRepository);
    private final SubscriptionPlanController subscriptionPlanController = new SubscriptionPlanController(createSubscriptionPlan, getSubscriptionPlan);
    private final CreateSubscriptionPlanCommand createSubscriptionPlanCommand = new CreateSubscriptionPlanCommand(100d, Period.Yearly);

    @Nested
    class CreateSubscriptionPlanShould {
        @Test
        void return_generation_id_with_status_ok() {
            final ResponseEntity<UUID> responseEntity = subscriptionPlanController.createSubscriptionPlan(createSubscriptionPlanCommand);

            assertThat(responseEntity.getBody()).isEqualTo(expectedUUID);
            assertThat(responseEntity.getStatusCode()).isEqualTo(OK);
        }

        @Test
        void save_new_subscriptionPlan() {
            subscriptionPlanController.createSubscriptionPlan(createSubscriptionPlanCommand);

            final SubscriptionPlanId subscriptionPlanId = new SubscriptionPlanId(expectedUUID);
            assertThat(subscriptionPlanRepository.findById(subscriptionPlanId)).isEqualToComparingFieldByField(SubscriptionPlan.createYearly(subscriptionPlanId, 100d));
        }
    }

    @Nested
    class FindByIdShould {
        @Test
        void return_subscriptionPlan_when_found() {
            final UUID fixedUUID = UUID.randomUUID();
            final SubscriptionPlan subscriptionPlan = SubscriptionPlan.createYearly(new SubscriptionPlanId(fixedUUID), 100d);
            ((InMemorySubscriptionPlanRepository) subscriptionPlanRepository).add(subscriptionPlan);

            final ResponseEntity<SubscriptionPlan> responseEntity = subscriptionPlanController.findById(fixedUUID.toString());

            assertThat(responseEntity.getBody()).isEqualToComparingFieldByField(subscriptionPlan);
            assertThat(responseEntity.getStatusCode()).isEqualTo(OK);
        }

        @Test
        void return_empty_when_not_found() {
            final UUID fixedUUID = UUID.randomUUID();

            final ResponseEntity<SubscriptionPlan> responseEntity = subscriptionPlanController.findById(fixedUUID.toString());

            assertThat(responseEntity.getBody()).isNull();
            assertThat(responseEntity.getStatusCode()).isEqualTo(NOT_FOUND);
        }
    }
}
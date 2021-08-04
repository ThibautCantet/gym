package com.gym.use_case;

import com.gym.domain.subscription_plan.*;
import com.gym.infrastructure.InMemorySubscriptionPlanRepository;
import org.junit.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateSubscriptionPlanUTest {

    @Test
    public void execute_should_save_new_monthly_subscription_plan() {
        final UUID id = UUID.randomUUID();
        final InMemorySubscriptionPlanRepository subscriptionPlanRepository = new InMemorySubscriptionPlanRepository(id);
        final CreateSubscriptionPlan createSubscriptionPlan = new CreateSubscriptionPlan(subscriptionPlanRepository);
        final BasePrice basePrice = new BasePrice(10d);
        final Period period = Period.Monthly;

        createSubscriptionPlan.execute(basePrice, period);

        assertThat(subscriptionPlanRepository.getAllSubscriptionPlan())
                .usingElementComparatorIgnoringFields("discountRate")
                .containsExactly(new SubscriptionPlan(new SubscriptionPlanId(id), basePrice, period));

        final SubscriptionPlan subscriptionPlan = subscriptionPlanRepository.getAllSubscriptionPlan().get(0);
        assertThat(subscriptionPlan.getBasePrice()).isEqualTo(basePrice);
        assertThat(subscriptionPlan.getTotalPrice()).isEqualTo(new TotalPrice(basePrice.amount()));
        assertThat(subscriptionPlan.getPeriod()).isEqualTo(period);
        assertThat(subscriptionPlan.getDiscountRate()).isEqualToComparingFieldByField(new DiscountRate(Period.Monthly));
    }

    @Test
    public void execute_should_apply_10_percent_discount_and_save_new_yearly_subscription_plan() {
        final UUID id = UUID.randomUUID();
        final InMemorySubscriptionPlanRepository subscriptionPlanRepository = new InMemorySubscriptionPlanRepository(id);
        final CreateSubscriptionPlan createSubscriptionPlan = new CreateSubscriptionPlan(subscriptionPlanRepository);
        final BasePrice basePrice = new BasePrice(10d);
        final Period period = Period.Yearly;

        createSubscriptionPlan.execute(basePrice, period);

        assertThat(subscriptionPlanRepository.getAllSubscriptionPlan())
                .usingElementComparatorIgnoringFields("discountRate")
                .containsExactly(new SubscriptionPlan(new SubscriptionPlanId(id), basePrice, period));

        final SubscriptionPlan subscriptionPlan = subscriptionPlanRepository.getAllSubscriptionPlan().get(0);
        assertThat(subscriptionPlan.getBasePrice()).isEqualTo(basePrice);
        assertThat(subscriptionPlan.getTotalPrice()).isEqualTo(new TotalPrice(9d));
        assertThat(subscriptionPlan.getPeriod()).isEqualTo(period);
        assertThat(subscriptionPlan.getDiscountRate()).isEqualToComparingFieldByField(new DiscountRate(10d));
    }
}
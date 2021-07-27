package com.gym.use_case;

import com.gym.domain.subscriber.Subscriber;
import com.gym.domain.subscriber.Subscription;
import com.gym.domain.subscriber.SubscriptionRepository;
import com.gym.domain.subscription_plan.BasePrice;
import com.gym.domain.subscription_plan.Period;
import com.gym.domain.subscription_plan.SubscriptionPlan;
import com.gym.infrastructure.InMemorySubscriptionRepository;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

public class ShowOnGoingSubscriptionsAmountUTest {

    @Test
    public void execute_should_return_zero_when_no_on_going_subscription() {
        final SubscriptionRepository subscriptionRepository = new InMemorySubscriptionRepository();

        final ShowOnGoingSubscriptionsAmount showOnGoingSubscriptionsAmount = new ShowOnGoingSubscriptionsAmount(subscriptionRepository);
        final Month month = Month.JULY;
        final int year = 2021;

        final Double result = showOnGoingSubscriptionsAmount.execute(month, year);

        assertThat(result).isEqualTo(0d);
    }

    @Test
    public void execute_should_return_ongoing_subscriptions_for_a_given_month() {
        final SubscriptionRepository subscriptionRepository = new InMemorySubscriptionRepository();

        Subscriber subscriber = Subscriber.createStandard();

        SubscriptionPlan monthlySubscriptionPlan = new SubscriptionPlan(new BasePrice(90d), Period.Monthly);
        LocalDate january = LocalDate.of(2021, Month.JANUARY, 1);
        final Subscription januaryMonthlySubscription = new Subscription(monthlySubscriptionPlan, subscriber, january);
        ((InMemorySubscriptionRepository) subscriptionRepository).getSubscriptions().add(januaryMonthlySubscription);

        LocalDate july = LocalDate.of(2021, Month.JULY, 1);
        SubscriptionPlan monthlySubscriptionPlan2 = new SubscriptionPlan(new BasePrice(100d), Period.Monthly);
        final Subscription julyMonthlySubscription = new Subscription(monthlySubscriptionPlan2, subscriber, july);
        ((InMemorySubscriptionRepository) subscriptionRepository).getSubscriptions().add(julyMonthlySubscription);

        final ShowOnGoingSubscriptionsAmount showOnGoingSubscriptionsAmount = new ShowOnGoingSubscriptionsAmount(subscriptionRepository);
        final Month month = Month.JULY;
        final int year = 2021;

        final Double result = showOnGoingSubscriptionsAmount.execute(month, year);

        final double expectedOngoingSubscriptions = 100d;
        assertThat(result).isEqualTo(expectedOngoingSubscriptions);
    }
}

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

    public static final Month MONTH = Month.JULY;
    public static final int YEAR = 2021;
    private final SubscriptionRepository subscriptionRepository = new InMemorySubscriptionRepository();

    @Test
    public void execute_should_return_zero_when_no_on_going_subscription() {
        // given
        final ShowOnGoingSubscriptionsAmount showOnGoingSubscriptionsAmount = new ShowOnGoingSubscriptionsAmount(subscriptionRepository);

        // when
        final Double result = showOnGoingSubscriptionsAmount.execute(MONTH, YEAR);

        // then
        assertThat(result).isEqualTo(0d);
    }

    @Test
    public void execute_should_return_ongoing_subscriptions_for_a_given_month() {
        // given
        Subscriber subscriber = Subscriber.createStandard();
        createSubscription(subscriber, 90d, Month.JANUARY);
        createSubscription(subscriber, 100d, Month.JULY);
        final ShowOnGoingSubscriptionsAmount showOnGoingSubscriptionsAmount = new ShowOnGoingSubscriptionsAmount(subscriptionRepository);

        // when
        final Double result = showOnGoingSubscriptionsAmount.execute(MONTH, YEAR);

        // then
        final double expectedOngoingSubscriptions = 100d;
        assertThat(result).isEqualTo(expectedOngoingSubscriptions);
    }

    private void createSubscription(Subscriber subscriber, double basePrice, Month month) {
        SubscriptionPlan monthlySubscriptionPlan = new SubscriptionPlan(new BasePrice(basePrice), Period.Monthly);
        LocalDate january = LocalDate.of(2021, month, 1);
        final Subscription januaryMonthlySubscription = new Subscription(monthlySubscriptionPlan, subscriber, january);
        ((InMemorySubscriptionRepository) subscriptionRepository).getSubscriptions().add(januaryMonthlySubscription);
    }
}

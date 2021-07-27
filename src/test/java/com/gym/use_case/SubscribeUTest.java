package com.gym.use_case;

import com.gym.domain.subscriber.Price;
import com.gym.domain.subscriber.Subscriber;
import com.gym.domain.subscriber.Subscription;
import com.gym.domain.subscriber.SubscriptionRepository;
import com.gym.domain.subscription_plan.BasePrice;
import com.gym.domain.subscription_plan.DiscountRate;
import com.gym.domain.subscription_plan.Period;
import com.gym.domain.subscription_plan.SubscriptionPlan;
import com.gym.infrastructure.InMemorySubscriptionRepository;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SubscribeUTest {

    @Test
    public void execute_should_save_new_monthly_subscription_with_20_percent_discount_when_subscriber_is_a_student() {
        SubscriptionRepository subscriptionRepository = new InMemorySubscriptionRepository();
        final Subscribe subscribe = new Subscribe(subscriptionRepository);
        final SubscriptionPlan subscriptionPlan = new SubscriptionPlan(new BasePrice(100d), Period.Monthly);
        final Subscriber subscriber = Subscriber.createStudent();
        final LocalDate date = LocalDate.now();

        subscribe.execute(subscriptionPlan, subscriber);

        final List<Subscription> subscriptions = ((InMemorySubscriptionRepository) subscriptionRepository).getSubscriptions();
        assertThat(subscriptions)
                .usingElementComparatorIgnoringFields("subscriptionPlan", "subscriber", "discountRate")
                .containsExactly(new Subscription(subscriptionPlan, subscriber, date));

        final Subscription subscription = subscriptions.get(0);
        assertThat(subscription.getDiscountRate()).isEqualToComparingFieldByField(new DiscountRate(20d));
        assertThat(subscription.getPrice()).isEqualTo(new Price(80d));
    }

    @Test
    public void execute_should_save_new_monthly_subscription_without_discount_when_subscriber_is_not_a_student() {
        SubscriptionRepository subscriptionRepository = new InMemorySubscriptionRepository();
        final Subscribe subscribe = new Subscribe(subscriptionRepository);
        final SubscriptionPlan subscriptionPlan = new SubscriptionPlan(new BasePrice(100d), Period.Monthly);
        final Subscriber subscriber = Subscriber.createStandard();
        final LocalDate date = LocalDate.now();

        subscribe.execute(subscriptionPlan, subscriber);

        final List<Subscription> subscriptions = ((InMemorySubscriptionRepository) subscriptionRepository).getSubscriptions();
        assertThat(subscriptions)
                .usingElementComparatorIgnoringFields("subscriptionPlan", "subscriber", "discountRate")
                .containsExactly(new Subscription(subscriptionPlan, subscriber, date));

        final Subscription subscription = subscriptions.get(0);
        assertThat(subscription.getDiscountRate()).isEqualToComparingFieldByField(new DiscountRate(0d));
        assertThat(subscription.getPrice()).isEqualTo(new Price(100d));
    }

    @Test
    public void execute_should_save_new_yearly_subscription_without_discount_when_subscriber_is_not_a_student() {
        SubscriptionRepository subscriptionRepository = new InMemorySubscriptionRepository();
        final Subscribe subscribe = new Subscribe(subscriptionRepository);
        final SubscriptionPlan subscriptionPlan = new SubscriptionPlan(new BasePrice(100d), Period.Yearly);
        final Subscriber subscriber = Subscriber.createStandard();
        final LocalDate date = LocalDate.now();

        subscribe.execute(subscriptionPlan, subscriber);

        final List<Subscription> subscriptions = ((InMemorySubscriptionRepository) subscriptionRepository).getSubscriptions();
        assertThat(subscriptions)
                .usingElementComparatorIgnoringFields("subscriptionPlan", "subscriber", "discountRate")
                .containsExactly(new Subscription(subscriptionPlan, subscriber, date));

        final Subscription subscription = subscriptions.get(0);
        assertThat(subscription.getDiscountRate()).isEqualToComparingFieldByField(new DiscountRate(10d));
        assertThat(subscription.getPrice()).isEqualTo(new Price(90d));
    }
}
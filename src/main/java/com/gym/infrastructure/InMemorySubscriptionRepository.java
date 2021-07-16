package com.gym.infrastructure;

import com.gym.domain.subscriber.Subscription;
import com.gym.domain.subscriber.SubscriptionRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemorySubscriptionRepository implements SubscriptionRepository {
    private final List<Subscription> subscriptions = new ArrayList<>();

    @Override
    public void save(Subscription subscription) {
        subscriptions.add(subscription);
    }

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }
}

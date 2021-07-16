package com.gym.infrastructure;

import com.gym.domain.subscriber.Subscription;
import com.gym.domain.subscriber.SubscriptionId;
import com.gym.domain.subscriber.SubscriptionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InMemorySubscriptionRepository implements SubscriptionRepository {
    private final List<Subscription> subscriptions = new ArrayList<>();
    private final UUID fixedUUID;

    public InMemorySubscriptionRepository(UUID fixedUUID) {
        this.fixedUUID = fixedUUID;
    }

    @Override
    public SubscriptionId next() {
        return new SubscriptionId(fixedUUID);
    }

    @Override
    public void save(Subscription subscription) {
        subscriptions.add(subscription);
    }

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }
}

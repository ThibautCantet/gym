package com.gym.infrastructure;

import com.gym.domain.subscriber.Subscription;
import com.gym.domain.subscriber.SubscriptionId;
import com.gym.domain.subscriber.SubscriptionRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class InMemorySubscriptionRepository implements SubscriptionRepository {
    private final HashMap<SubscriptionId, Subscription> subscriptions = new HashMap();
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
        subscriptions.put(subscription.getSubscriptionId(), subscription);
    }

    @Override
    public List<Subscription> findAll() {
        return subscriptions.values().stream().toList();
    }

    @Override
    public void saveAll(List<Subscription> subscriptionsToRenew) {
        subscriptionsToRenew.stream()
                .forEach(subscription ->
                        subscriptions.put(subscription.getSubscriptionId(), subscription));
    }

    public List<Subscription> getSubscriptions() {
        return subscriptions.values().stream().toList();
    }

    public void addSubscriptions(List<Subscription> subscriptions) {
        subscriptions
                .forEach(subscription -> this.subscriptions.put(subscription.getSubscriptionId(), subscription));
    }
}

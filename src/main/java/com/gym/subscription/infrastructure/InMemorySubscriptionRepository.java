package com.gym.subscription.infrastructure;

import com.gym.subscription.domain.Subscription;
import com.gym.subscription.domain.SubscriptionId;
import com.gym.subscription.domain.SubscriptionRepository;

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

    public Subscription findById(SubscriptionId subscriptionId) {
        return subscriptions.get(subscriptionId);
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
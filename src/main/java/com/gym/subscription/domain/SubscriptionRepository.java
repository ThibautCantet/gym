package com.gym.subscription.domain;

import java.util.List;

public interface SubscriptionRepository {
    SubscriptionId next();

    void save(Subscription subscription);

    List<Subscription> findAll();

    void saveAll(List<Subscription> subscriptionsToRenew);
}

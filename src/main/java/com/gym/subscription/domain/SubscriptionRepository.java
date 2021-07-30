package com.gym.subscription.domain;

import com.gym.ddd.Repository;

import java.util.List;

public interface SubscriptionRepository extends Repository {
    SubscriptionId next();

    void save(Subscription subscription);

    List<Subscription> findAll();

    void saveAll(List<Subscription> subscriptionsToRenew);
}

package com.gym.domain.subscriber;

public interface SubscriptionRepository {
    SubscriptionId next();

    void save(Subscription subscription);
}

package com.gym.domain.subscriber;

import java.util.List;

public interface SubscriptionRepository {
    SubscriptionId next();

    void save(Subscription subscription);

    List<Subscription> findAll();
}

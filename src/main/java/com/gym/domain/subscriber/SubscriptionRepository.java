package com.gym.domain.subscriber;

import java.util.List;

public interface SubscriptionRepository {
    void save(Subscription subscription);

    List<Subscription> findAll();
}

package com.gym.domain.subscriber;

import java.util.List;
import java.util.stream.Stream;

public interface SubscriptionRepository {
    SubscriptionId next();

    void save(Subscription subscription);

    List<Subscription> findAll();

    void saveAll(List<Subscription> subscriptionsToRenew);
}

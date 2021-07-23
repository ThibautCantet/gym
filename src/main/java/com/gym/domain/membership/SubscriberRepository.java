package com.gym.domain.membership;

public interface SubscriberRepository {
    SubscriberId next();

    void save(Subscriber subscriber);
}

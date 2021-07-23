package com.gym.infrastructure;

import com.gym.domain.membership.SubscriberRepository;
import com.gym.domain.membership.Subscriber;
import com.gym.domain.membership.SubscriberId;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class InMemorySubscriberRepository implements SubscriberRepository {
    private UUID fixedUUID;
    private final HashMap<SubscriberId, Subscriber> subscribers = new HashMap<>();

    public InMemorySubscriberRepository(UUID randomUUID) {
        fixedUUID = randomUUID;
    }

    public List<Subscriber> getSubscribers() {
        return subscribers.values().stream().toList();
    }

    @Override
    public SubscriberId next() {
        return new SubscriberId(fixedUUID);
    }

    @Override
    public void save(Subscriber subscriber) {
        subscribers.put(subscriber.getId(), subscriber);
    }
}

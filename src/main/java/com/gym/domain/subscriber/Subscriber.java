package com.gym.domain.subscriber;

public class Subscriber {
    private final SubscriberId subscriberId;
    private final boolean isStudent;

    private Subscriber(SubscriberId subscriberId, boolean isStudent) {
        this.subscriberId = subscriberId;
        this.isStudent = isStudent;
    }

    public SubscriberId getId() {
        return subscriberId;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public static Subscriber createRegular(SubscriberId subscriberId) {
        return new Subscriber(subscriberId, false);
    }

    public static Subscriber createStudent(SubscriberId subscriberId) {
        return new Subscriber(subscriberId, true);
    }
}

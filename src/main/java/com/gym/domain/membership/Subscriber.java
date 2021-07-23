package com.gym.domain.membership;

public class Subscriber {
    private final SubscriberId subscriberId;
    private final Email email;
    private final boolean isStudent;

    private Subscriber(SubscriberId subscriberId, Email email, boolean isStudent) {
        this.subscriberId = subscriberId;
        this.email = email;
        this.isStudent = isStudent;
    }

    public SubscriberId getId() {
        return subscriberId;
    }

    public Email getEmail() {
        return email;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public static Subscriber createRegular(SubscriberId subscriberId, Email email) {
        return new Subscriber(subscriberId, email, false);
    }

    public static Subscriber createStudent(SubscriberId subscriberId, Email email) {
        return new Subscriber(subscriberId, email, true);
    }
}

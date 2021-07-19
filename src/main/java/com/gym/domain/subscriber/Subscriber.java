package com.gym.domain.subscriber;

public class Subscriber {

    private final boolean isStudent;

    private Subscriber(boolean isStudent) {
        this.isStudent = isStudent;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public static Subscriber createStudent() {
        return new Subscriber(true);
    }

    public static Subscriber createStandard() {
        return new Subscriber(false);
    }
}

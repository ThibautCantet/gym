package com.gym.membership.domain;

public interface Mailer {
    void sentWelcomeEmail(Member member);
}
package com.gym.domain.subscription;

import com.gym.domain.membership.Mailer;
import com.gym.domain.membership.Member;

public class DoubleMailer implements Mailer {
    private Boolean hasSentWelcomeEmail = false;

    public Boolean hasSentWelcomeEmail() {
        return hasSentWelcomeEmail;
    }

    @Override
    public void sentWelcomeEmail(Member member) {
        hasSentWelcomeEmail = true;
    }
}

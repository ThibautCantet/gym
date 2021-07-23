package com.gym.domain.subscriber;

import com.gym.domain.membership.Mailer;
import com.gym.domain.membership.Subscriber;

public class DoubleMailer implements Mailer {
    private Boolean hasSentWelcomeEmail = false;

    public Boolean hasSentWelcomeEmail() {
        return hasSentWelcomeEmail;
    }

    @Override
    public void sentWelcomeEmail(Subscriber subscriber) {
        hasSentWelcomeEmail = true;
    }
}

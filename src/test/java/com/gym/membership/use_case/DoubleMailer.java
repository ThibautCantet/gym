package com.gym.membership.use_case;

import com.gym.membership.domain.Mailer;
import com.gym.membership.domain.Member;

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

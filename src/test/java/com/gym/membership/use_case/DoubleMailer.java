package com.gym.membership.use_case;

import com.gym.membership.domain.Email;
import com.gym.membership.domain.Mailer;
import com.gym.membership.domain.Member;

public class DoubleMailer implements Mailer {
    private Boolean hasSentWelcomeEmail = false;
    private Boolean hasSentSummaryEmail = false;

    public Boolean hasSentWelcomeEmail() {
        return hasSentWelcomeEmail;
    }

    public boolean hasSentSummaryEmail() {
        return hasSentSummaryEmail;
    }

    @Override
    public void sentWelcomeEmail(Member member) {
        hasSentWelcomeEmail = true;
    }

    @Override
    public void sendSummaryEmail(Email email) {
        hasSentSummaryEmail = true;
    }
}

package com.gym.membership.use_case;

import com.gym.membership.domain.Email;
import com.gym.membership.domain.Mailer;

public class SendSummaryEmailForNewSubscription {
    private final Mailer mailer;

    public SendSummaryEmailForNewSubscription(Mailer mailer) {
        this.mailer = mailer;
    }

    public void execute(Email email) {
        mailer.sendSummaryEmail(email);
    }
}

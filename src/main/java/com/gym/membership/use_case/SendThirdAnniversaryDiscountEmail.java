package com.gym.membership.use_case;

import com.gym.membership.domain.Email;
import com.gym.membership.domain.Mailer;

public class SendThirdAnniversaryDiscountEmail {
    private final Mailer mailer;

    public SendThirdAnniversaryDiscountEmail(Mailer mailer) {
        this.mailer = mailer;
    }

    public void handle(SendThirdAnniversaryDiscountEmailCommand sendThirdAnniversaryDiscountEmailCommand) {
        mailer.sendThirdAnniversaryDiscountEmail(new Email(sendThirdAnniversaryDiscountEmailCommand.email()));
    }
}

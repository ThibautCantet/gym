package com.gym.membership.use_case;

import com.gym.membership.domain.Email;
import com.gym.membership.domain.Mailer;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SendThirdAnniversaryDiscountEmailUTest {
    @Test
    void execute_should_send_third_anniversary_discount_email() {
        final Mailer mailer = new DoubleMailer();
        final SendThirdAnniversaryDiscountEmail sendSummaryEmailForNewSubscription = new SendThirdAnniversaryDiscountEmail(mailer);
        final Email email = new Email("thibaut.cantet@kata.fr");

        sendSummaryEmailForNewSubscription.execute(email);

        assertThat(((DoubleMailer) mailer).hasSentThirdAnniversaryDiscountEmail()).isTrue();
    }
}
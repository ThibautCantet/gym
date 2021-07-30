package com.gym.membership.use_case;

import com.gym.membership.domain.Email;
import com.gym.membership.domain.Mailer;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SendSummaryEmailForNewSubscriptionUTest {

    @Test
    void execute_should_send_summary_email_for_new_subscription() {
        final Mailer mailer = new DoubleMailer();
        final SendSummaryEmailForNewSubscription sendSummaryEmailForNewSubscription = new SendSummaryEmailForNewSubscription(mailer);
        final Email email = new Email("thibaut.cantet@kata.fr");

        sendSummaryEmailForNewSubscription.execute(email);

        assertThat(((DoubleMailer) mailer).hasSentSummaryEmail()).isTrue();
    }
}
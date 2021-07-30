package com.gym.membership.use_case;

import com.gym.membership.domain.Mailer;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SendSummaryEmailForNewSubscriptionUTest {

    @Test
    void handle_should_send_summary_email_for_new_subscription() {
        final Mailer mailer = new DoubleMailer();
        final SendSummaryEmailForNewSubscription sendSummaryEmailForNewSubscription = new SendSummaryEmailForNewSubscription(mailer);
        final String email = "thibaut.cantet@kata.fr";
        final SendSummaryEmailForNewSubscriptionCommand sendSummaryEmailForNewSubscriptionCommand = new SendSummaryEmailForNewSubscriptionCommand(email);

        sendSummaryEmailForNewSubscription.handle(sendSummaryEmailForNewSubscriptionCommand);

        assertThat(((DoubleMailer) mailer).hasSentSummaryEmail()).isTrue();
    }
}
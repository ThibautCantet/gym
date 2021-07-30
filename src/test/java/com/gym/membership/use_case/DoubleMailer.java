package com.gym.membership.use_case;

import com.gym.membership.domain.Email;
import com.gym.membership.domain.Mailer;
import com.gym.membership.domain.Member;

public class DoubleMailer implements Mailer {
    private Boolean hasSentWelcomeEmail = false;
    private Boolean hasSentSummaryEmail = false;
    private Boolean hasSentThirdAnniversaryDiscountEmail = false;

    public Boolean hasSentWelcomeEmail() {
        return hasSentWelcomeEmail;
    }

    public boolean hasSentSummaryEmail() {
        return hasSentSummaryEmail;
    }

    public boolean hasSentThirdAnniversaryDiscountEmail() {
        return hasSentThirdAnniversaryDiscountEmail;
    }

    @Override
    public void sentWelcomeEmail(Member member) {
        hasSentWelcomeEmail = true;
    }

    @Override
    public void sendSummaryEmail(Email email) {
        hasSentSummaryEmail = true;
    }

    @Override
    public void sendThirdAnniversaryDiscountEmail(Email email) {
        hasSentThirdAnniversaryDiscountEmail = true;
    }
}

package com.gym.subscription.domain;

import java.time.LocalDate;

public record SubscriptionDate(LocalDate startDate, LocalDate endDate) {
    public boolean isOnGoing(LocalDate searchedDate) {
        return searchedDate.isEqual(startDate) || searchedDate.isAfter(startDate);
    }

    public SubscriptionDate renew() {
        return new SubscriptionDate(startDate, endDate.plusMonths(1));
    }

    public boolean hasItThirdAnniversary(LocalDate today) {
        return startDate.plusYears(3).isEqual(today);
    }
}

package com.gym.domain.subscriber;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record SubscriptionDate(LocalDateTime dateTime) {
    public boolean isOnGoing(LocalDate searchedDate) {
        return searchedDate.isEqual(dateTime.toLocalDate()) || searchedDate.isAfter(dateTime.toLocalDate());
    }
}

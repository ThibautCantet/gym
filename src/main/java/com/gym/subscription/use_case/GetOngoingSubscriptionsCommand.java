package com.gym.subscription.use_case;

import java.time.LocalDate;

public record GetOngoingSubscriptionsCommand(LocalDate localDate) {
}

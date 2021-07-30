package com.gym.membership.use_case;

public record RegisterNewMemberCommand(String email, boolean isStudent) {
}

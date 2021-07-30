package com.gym.membership.domain;

import com.gym.ddd.Aggregate;

public class Member implements Aggregate {
    private final MemberId memberId;
    private final Email email;
    private final boolean isStudent;

    private Member(MemberId memberId, String email, boolean isStudent) {
        this.memberId = memberId;
        this.email = new Email(email);
        this.isStudent = isStudent;
    }

    public MemberId getId() {
        return memberId;
    }

    public Email getEmail() {
        return email;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public static Member createRegular(MemberId memberId, String email) {
        return new Member(memberId, email, false);
    }

    public static Member createStudent(MemberId memberId, String email) {
        return new Member(memberId, email, true);
    }
}

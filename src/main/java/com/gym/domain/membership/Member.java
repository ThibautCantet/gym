package com.gym.domain.membership;

public class Member {
    private final MemberId memberId;
    private final Email email;
    private final boolean isStudent;

    private Member(MemberId memberId, Email email, boolean isStudent) {
        this.memberId = memberId;
        this.email = email;
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

    public static Member createRegular(MemberId memberId, Email email) {
        return new Member(memberId, email, false);
    }

    public static Member createStudent(MemberId memberId, Email email) {
        return new Member(memberId, email, true);
    }
}

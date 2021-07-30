package com.gym.membership.domain;

public interface MemberRepository {
    MemberId next();

    void save(Member member);

    Member findById(MemberId memberId);
}

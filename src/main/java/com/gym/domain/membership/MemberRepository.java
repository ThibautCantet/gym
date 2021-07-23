package com.gym.domain.membership;

public interface MemberRepository {
    MemberId next();

    void save(Member member);
}

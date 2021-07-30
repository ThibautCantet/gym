package com.gym.membership.domain;

import com.gym.ddd.Repository;

public interface MemberRepository extends Repository {
    MemberId next();

    void save(Member member);

    Member findById(MemberId memberId);
}

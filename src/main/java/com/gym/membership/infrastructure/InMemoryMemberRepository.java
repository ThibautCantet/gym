package com.gym.membership.infrastructure;

import com.gym.membership.domain.MemberRepository;
import com.gym.membership.domain.Member;
import com.gym.membership.domain.MemberId;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class InMemoryMemberRepository implements MemberRepository {
    private final UUID fixedUUID;
    private final HashMap<MemberId, Member> subscribers = new HashMap<>();

    public InMemoryMemberRepository(UUID randomUUID) {
        fixedUUID = randomUUID;
    }

    public List<Member> getSubscribers() {
        return subscribers.values().stream().toList();
    }

    @Override
    public MemberId next() {
        return new MemberId(fixedUUID);
    }

    @Override
    public void save(Member member) {
        subscribers.put(member.getId(), member);
    }

    @Override
    public Member findById(MemberId memberId) {
        return subscribers.get(memberId);
    }
}

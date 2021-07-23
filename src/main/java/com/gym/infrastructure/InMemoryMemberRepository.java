package com.gym.infrastructure;

import com.gym.domain.membership.MemberRepository;
import com.gym.domain.membership.Member;
import com.gym.domain.membership.MemberId;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class InMemoryMemberRepository implements MemberRepository {
    private UUID fixedUUID;
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
}

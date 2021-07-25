package com.gym.membership.use_case;

import com.gym.membership.domain.Email;
import com.gym.membership.domain.Mailer;
import com.gym.membership.domain.MemberRepository;
import com.gym.membership.domain.Member;
import com.gym.membership.domain.MemberId;

public class RegisterNewMember {
    private final MemberRepository memberRepository;
    private final Mailer mailer;

    public RegisterNewMember(MemberRepository memberRepository, Mailer mailer) {
        this.memberRepository = memberRepository;
        this.mailer = mailer;
    }

    public void execute(Email email, boolean isStudent) {
        MemberId memberId = memberRepository.next();

        final Member member;
        if (isStudent) {
            member = Member.createStudent(memberId, email);
        } else {
            member = Member.createRegular(memberId, email);
        }
        memberRepository.save(member);

        mailer.sentWelcomeEmail(member);
    }
}

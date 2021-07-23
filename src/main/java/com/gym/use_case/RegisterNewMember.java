package com.gym.use_case;

import com.gym.domain.membership.Email;
import com.gym.domain.membership.Mailer;
import com.gym.domain.membership.MemberRepository;
import com.gym.domain.membership.Member;
import com.gym.domain.membership.MemberId;

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

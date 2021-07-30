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

    public void handle(RegisterNewMemberCommand registerNewMemberCommand) {
        MemberId memberId = memberRepository.next();

        final Member member;
        if (registerNewMemberCommand.isStudent()) {
            member = Member.createStudent(memberId, registerNewMemberCommand.email());
        } else {
            member = Member.createRegular(memberId, registerNewMemberCommand.email());
        }
        memberRepository.save(member);

        mailer.sentWelcomeEmail(member);
    }
}

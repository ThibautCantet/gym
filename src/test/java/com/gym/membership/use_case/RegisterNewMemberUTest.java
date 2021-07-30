package com.gym.membership.use_case;

import com.gym.membership.domain.Mailer;
import com.gym.membership.domain.MemberRepository;
import com.gym.membership.domain.Member;
import com.gym.membership.domain.MemberId;
import com.gym.membership.infrastructure.*;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class RegisterNewMemberUTest {

    @Test
    void handle_should_send_email_to_new_member() {
        final Mailer mailer = new DoubleMailer();

        final MemberRepository memberRepository = new InMemoryMemberRepository(UUID.randomUUID());
        final RegisterNewMember registerNewMember = new RegisterNewMember(memberRepository, mailer);
        final String email = "thibaut.cantet@kata.fr";
        final RegisterNewMemberCommand registerNewMemberCommand = new RegisterNewMemberCommand(email, false);

        registerNewMember.handle(registerNewMemberCommand);

        assertThat(((DoubleMailer) mailer).hasSentWelcomeEmail()).isTrue();
    }

    @Test
    void handle_should_save_new_regular_member() {
        final Mailer mailer = new DoubleMailer();

        final UUID randomUUID = UUID.randomUUID();
        final MemberId memberId = new MemberId(randomUUID);

        final MemberRepository memberRepository = new InMemoryMemberRepository(randomUUID);
        final RegisterNewMember registerNewMember = new RegisterNewMember(memberRepository, mailer);
        final String email = "thibaut.cantet@kata.fr";
        final RegisterNewMemberCommand registerNewMemberCommand = new RegisterNewMemberCommand(email, false);

        registerNewMember.handle(registerNewMemberCommand);

        assertThat((((InMemoryMemberRepository) memberRepository).getSubscribers()))
                .usingElementComparatorIgnoringFields()
                .containsExactly(Member.createRegular(memberId, email));
    }

    @Test
    void handle_should_save_new_student_member() {
        final Mailer mailer = new DoubleMailer();

        final UUID randomUUID = UUID.randomUUID();
        final MemberId memberId = new MemberId(randomUUID);

        final MemberRepository memberRepository = new InMemoryMemberRepository(randomUUID);
        final RegisterNewMember registerNewMember = new RegisterNewMember(memberRepository, mailer);
        final String email = "thibaut.cantet@kata.fr";
        final RegisterNewMemberCommand registerNewMemberCommand = new RegisterNewMemberCommand(email, true);

        registerNewMember.handle(registerNewMemberCommand);

        assertThat((((InMemoryMemberRepository) memberRepository).getSubscribers()))
                .usingElementComparatorIgnoringFields()
                .containsExactly(Member.createStudent(memberId, email));
    }
}
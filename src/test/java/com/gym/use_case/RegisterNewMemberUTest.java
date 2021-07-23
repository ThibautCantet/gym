package com.gym.use_case;

import com.gym.domain.subscription.DoubleMailer;
import com.gym.domain.membership.Email;
import com.gym.domain.membership.Mailer;
import com.gym.domain.membership.MemberRepository;
import com.gym.domain.membership.Member;
import com.gym.domain.membership.MemberId;
import com.gym.infrastructure.InMemoryMemberRepository;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class RegisterNewMemberUTest {

    @Test
    void execute_should_send_email_to_new_member() {
        final Mailer mailer = new DoubleMailer();

        final MemberRepository memberRepository = new InMemoryMemberRepository(UUID.randomUUID());
        final RegisterNewMember registerNewMember = new RegisterNewMember(memberRepository, mailer);
        final Email email = new Email("thibaut.cantet@kata.fr");
        final boolean isStudent = false;

        registerNewMember.execute(email, isStudent);

        assertThat(((DoubleMailer) mailer).hasSentWelcomeEmail()).isTrue();
    }

    @Test
    void execute_should_save_new_regular_member() {
        final Mailer mailer = new DoubleMailer();

        final UUID randomUUID = UUID.randomUUID();
        final MemberId memberId = new MemberId(randomUUID);

        final MemberRepository memberRepository = new InMemoryMemberRepository(randomUUID);
        final RegisterNewMember registerNewMember = new RegisterNewMember(memberRepository, mailer);
        final Email email = new Email("thibaut.cantet@kata.fr");
        final boolean isStudent = false;

        registerNewMember.execute(email, isStudent);

        assertThat((((InMemoryMemberRepository) memberRepository).getSubscribers()))
                .usingElementComparatorIgnoringFields()
                .containsExactly(Member.createRegular(memberId, email));
    }

    @Test
    void execute_should_save_new_student_member() {
        final Mailer mailer = new DoubleMailer();

        final UUID randomUUID = UUID.randomUUID();
        final MemberId memberId = new MemberId(randomUUID);

        final MemberRepository memberRepository = new InMemoryMemberRepository(randomUUID);
        final RegisterNewMember registerNewMember = new RegisterNewMember(memberRepository, mailer);
        final Email email = new Email("thibaut.cantet@kata.fr");
        final boolean isStudent = true;

        registerNewMember.execute(email, isStudent);

        assertThat((((InMemoryMemberRepository) memberRepository).getSubscribers()))
                .usingElementComparatorIgnoringFields()
                .containsExactly(Member.createStudent(memberId, email));
    }
}
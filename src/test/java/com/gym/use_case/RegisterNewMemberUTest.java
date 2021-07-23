package com.gym.use_case;

import com.gym.domain.subscriber.DoubleMailer;
import com.gym.domain.membership.Email;
import com.gym.domain.membership.Mailer;
import com.gym.domain.membership.SubscriberRepository;
import com.gym.domain.membership.Subscriber;
import com.gym.domain.membership.SubscriberId;
import com.gym.infrastructure.InMemorySubscriberRepository;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class RegisterNewMemberUTest {

    @Test
    void execute_should_send_email_to_new_member() {
        final Mailer mailer = new DoubleMailer();

        final SubscriberRepository subscriberRepository = new InMemorySubscriberRepository(UUID.randomUUID());
        final RegisterNewMember registerNewMember = new RegisterNewMember(subscriberRepository, mailer);
        final Email email = new Email("thibaut.cantet@kata.fr");
        final boolean isStudent = false;

        registerNewMember.execute(email, isStudent);

        assertThat(((DoubleMailer) mailer).hasSentWelcomeEmail()).isTrue();
    }

    @Test
    void execute_should_save_new_regular_member() {
        final Mailer mailer = new DoubleMailer();

        final UUID randomUUID = UUID.randomUUID();
        final SubscriberId subscriberId = new SubscriberId(randomUUID);

        final SubscriberRepository subscriberRepository = new InMemorySubscriberRepository(randomUUID);
        final RegisterNewMember registerNewMember = new RegisterNewMember(subscriberRepository, mailer);
        final Email email = new Email("thibaut.cantet@kata.fr");
        final boolean isStudent = false;

        registerNewMember.execute(email, isStudent);

        assertThat((((InMemorySubscriberRepository) subscriberRepository).getSubscribers()))
                .usingElementComparatorIgnoringFields()
                .containsExactly(Subscriber.createRegular(subscriberId, email));
    }

    @Test
    void execute_should_save_new_student_member() {
        final Mailer mailer = new DoubleMailer();

        final UUID randomUUID = UUID.randomUUID();
        final SubscriberId subscriberId = new SubscriberId(randomUUID);

        final SubscriberRepository subscriberRepository = new InMemorySubscriberRepository(randomUUID);
        final RegisterNewMember registerNewMember = new RegisterNewMember(subscriberRepository, mailer);
        final Email email = new Email("thibaut.cantet@kata.fr");
        final boolean isStudent = true;

        registerNewMember.execute(email, isStudent);

        assertThat((((InMemorySubscriberRepository) subscriberRepository).getSubscribers()))
                .usingElementComparatorIgnoringFields()
                .containsExactly(Subscriber.createStudent(subscriberId, email));
    }
}
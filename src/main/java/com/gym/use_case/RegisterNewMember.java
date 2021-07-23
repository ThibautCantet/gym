package com.gym.use_case;

import com.gym.domain.membership.Email;
import com.gym.domain.membership.Mailer;
import com.gym.domain.membership.SubscriberRepository;
import com.gym.domain.membership.Subscriber;
import com.gym.domain.membership.SubscriberId;

public class RegisterNewMember {
    private final SubscriberRepository subscriberRepository;
    private final Mailer mailer;

    public RegisterNewMember(SubscriberRepository subscriberRepository, Mailer mailer) {
        this.subscriberRepository = subscriberRepository;
        this.mailer = mailer;
    }

    public void execute(Email email, boolean isStudent) {
        SubscriberId subscriberId = subscriberRepository.next();

        final Subscriber subscriber;
        if (isStudent) {
            subscriber = Subscriber.createStudent(subscriberId, email);
        } else {
            subscriber = Subscriber.createRegular(subscriberId, email);
        }
        subscriberRepository.save(subscriber);

        mailer.sentWelcomeEmail(subscriber);
    }
}

package com.gym.subscription.use_case;

import com.gym.membership.domain.MemberId;
import com.gym.membership.domain.MemberRepository;
import com.gym.subscription.domain.Subscription;
import com.gym.subscription.domain.SubscriptionId;
import com.gym.subscription.domain.SubscriptionRepository;
import com.gym.membership.domain.Member;
import com.gym.subscription_plan.domain.*;

import java.time.Clock;

public class Subscribe {

    private final MemberRepository memberRepository;
    private final SubscriptionPlanRepository subscriptionPlanRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final Clock clock;

    public Subscribe(MemberRepository memberRepository, SubscriptionPlanRepository subscriptionPlanRepository, SubscriptionRepository subscriptionRepository, Clock clock) {
        this.memberRepository = memberRepository;
        this.subscriptionPlanRepository = subscriptionPlanRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.clock = clock;
    }

    public void execute(String memberId, String subscriptionPlanId) {
        final SubscriptionId subscriptionId = subscriptionRepository.next();
        final SubscriptionPlan subscriptionPlan = subscriptionPlanRepository.findById(new SubscriptionPlanId(subscriptionPlanId));
        final TotalPrice basePrice = subscriptionPlan.getTotalPrice();
        final Period period = subscriptionPlan.getPeriod();
        final Member member = memberRepository.findById(new MemberId(memberId));

        final Subscription subscription = Subscription.subscribe(subscriptionId, period, basePrice, member.isStudent(), clock);

        subscriptionRepository.save(subscription);
    }
}

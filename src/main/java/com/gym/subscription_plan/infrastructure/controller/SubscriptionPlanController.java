package com.gym.subscription_plan.infrastructure.controller;

import com.gym.subscription_plan.domain.SubscriptionPlan;
import com.gym.subscription_plan.use_case.CreateSubscriptionPlan;
import com.gym.subscription_plan.use_case.CreateSubscriptionPlanCommand;
import com.gym.subscription_plan.use_case.GetSubscriptionPlan;
import com.gym.subscription_plan.use_case.GetSubscriptionPlanCommand;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
public class SubscriptionPlanController {

    private final CreateSubscriptionPlan createSubscriptionPlan;
    private final GetSubscriptionPlan getSubscriptionPlan;

    public SubscriptionPlanController(CreateSubscriptionPlan createSubscriptionPlan, GetSubscriptionPlan getSubscriptionPlan) {
        this.createSubscriptionPlan = createSubscriptionPlan;
        this.getSubscriptionPlan = getSubscriptionPlan;
    }

    @PostMapping("/api/subscription-plan")
    public ResponseEntity<UUID> createSubscriptionPlan(@RequestBody CreateSubscriptionPlanCommand createSubscriptionPlanCommand) {
        return new ResponseEntity<>(createSubscriptionPlan.handle(createSubscriptionPlanCommand), HttpStatus.OK);
    }

    @GetMapping("/api/subscription-plan/{id}")
    public ResponseEntity<SubscriptionPlan> findById(@PathVariable("id") String subscriptionId) {
        final Optional<SubscriptionPlan> optionalSubscriptionPlan = getSubscriptionPlan.handle(new GetSubscriptionPlanCommand(subscriptionId));
        return optionalSubscriptionPlan.map(subscriptionPlan -> new ResponseEntity<>(subscriptionPlan, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}

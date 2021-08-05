package com.gym.subscription_plan.infrastructure.controller;

import com.gym.subscription_plan.use_case.CreateSubscriptionPlan;
import com.gym.subscription_plan.use_case.CreateSubscriptionPlanCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class SubscriptionPlanController {

    private final CreateSubscriptionPlan createSubscriptionPlan;

    public SubscriptionPlanController(CreateSubscriptionPlan createSubscriptionPlan) {
        this.createSubscriptionPlan = createSubscriptionPlan;
    }

    @PostMapping("/api/subscription-plan")
    public ResponseEntity<UUID> createSubscriptionPlan(@RequestBody CreateSubscriptionPlanCommand createSubscriptionPlanCommand) {
        return new ResponseEntity<>(createSubscriptionPlan.handle(createSubscriptionPlanCommand), HttpStatus.OK);
    }
}

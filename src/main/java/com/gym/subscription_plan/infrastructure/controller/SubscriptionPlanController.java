package com.gym.subscription_plan.infrastructure.controller;

import com.gym.subscription_plan.domain.SubscriptionPlan;
import com.gym.subscription_plan.domain.SubscriptionPlanId;
import com.gym.subscription_plan.use_case.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

@RestController
public class SubscriptionPlanController {

    private final CreateSubscriptionPlan createSubscriptionPlan;
    private final GetSubscriptionPlan getSubscriptionPlan;
    private final GetAllSubscriptionPlans getAllSubscriptionPlans;
    private final ChangeSubscriptionPlanPrice changeSubscriptionPlanPrice;

    public SubscriptionPlanController(CreateSubscriptionPlan createSubscriptionPlan, GetSubscriptionPlan getSubscriptionPlan, GetAllSubscriptionPlans getAllSubscriptionPlans, ChangeSubscriptionPlanPrice changeSubscriptionPlanPrice) {
        this.createSubscriptionPlan = createSubscriptionPlan;
        this.getSubscriptionPlan = getSubscriptionPlan;
        this.getAllSubscriptionPlans = getAllSubscriptionPlans;
        this.changeSubscriptionPlanPrice = changeSubscriptionPlanPrice;
    }

    @PostMapping("/api/subscription-plan")
    public ResponseEntity<UUID> createSubscriptionPlan(@RequestBody CreateSubscriptionPlanCommand createSubscriptionPlanCommand) {
        return new ResponseEntity<>(createSubscriptionPlan.handle(createSubscriptionPlanCommand), OK);
    }

    @GetMapping("/api/subscription-plan/{id}")
    public ResponseEntity<SubscriptionPlan> findById(@PathVariable("id") String subscriptionId) {
        final Optional<SubscriptionPlan> optionalSubscriptionPlan = getSubscriptionPlan.handle(new GetSubscriptionPlanCommand(subscriptionId));
        return optionalSubscriptionPlan.map(subscriptionPlan -> new ResponseEntity<>(subscriptionPlan, OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/api/subscription-plan")
    public ResponseEntity<List<SubscriptionPlan>> findAll() {
        List<SubscriptionPlan> subscriptionPlans = getAllSubscriptionPlans.handle(new GetAllSubscriptionPlansCommand());
        if (subscriptionPlans.isEmpty()) {
            return new ResponseEntity<>(NO_CONTENT);
        }
        return new ResponseEntity<>(subscriptionPlans, OK);
    }

    @PostMapping("/api/subscription-plan/{id}")
    public ResponseEntity<Void> changeSubscriptionPlanPrice(@PathVariable("id") String id, @RequestBody double newPrice) {
        final ChangeSubscriptionPlanPriceCommand changeSubscriptionPlanPriceCommand = new ChangeSubscriptionPlanPriceCommand(id, newPrice);
        Optional<SubscriptionPlanId> optionalSubscriptionPlanId = changeSubscriptionPlanPrice.handle(changeSubscriptionPlanPriceCommand);

        return optionalSubscriptionPlanId.map(subscriptionPlanId -> new ResponseEntity<Void>(NO_CONTENT))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }
}

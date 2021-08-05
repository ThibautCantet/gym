package com.gym.subscription_plan.infrastructure.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.gym.subscription_plan.domain.Period;
import com.gym.subscription_plan.infrastructure.controller.SubscriptionPlanController;
import com.gym.subscription_plan.use_case.CreateSubscriptionPlan;
import com.gym.subscription_plan.use_case.CreateSubscriptionPlanCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SubscriptionPlanController.class)
public class SubscriptionPlanControllerITest {

    @Autowired
    private SubscriptionPlanController subscriptionPlanController;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CreateSubscriptionPlan createSubscriptionPlan;
    private UUID fixedUUID;

    @BeforeEach
    void setUp() {
        fixedUUID = UUID.randomUUID();
        when(createSubscriptionPlan.handle(any())).thenReturn(fixedUUID);
    }

    @Test
    void createSubscriptionPlan_should_return_200() throws Exception {
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/subscription-plan/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(new CreateSubscriptionPlanCommand(100d, Period.Yearly))));

        resultActions.andExpect(status().isOk())
                .andExpect(content().string(fixedUUID.toString()));

    }

    private String toJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMobjectMapper = new ObjectMapper();
        objectMobjectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = objectMobjectMapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(object);
    }
}

package com.gym.subscription_plan.infrastructure.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.gym.subscription_plan.domain.Period;
import com.gym.subscription_plan.domain.SubscriptionPlan;
import com.gym.subscription_plan.domain.SubscriptionPlanId;
import com.gym.subscription_plan.use_case.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
    private MockMvc mockMvc;

    @MockBean
    private CreateSubscriptionPlan createSubscriptionPlan;

    @MockBean
    private GetSubscriptionPlan getSubscriptionPlan;

    private UUID fixedUUID;

    @BeforeEach
    void setUp() {
        fixedUUID = UUID.fromString("912eae98-15d7-4af0-8f8e-8c687c77a41b");
    }

    @Test
    void createSubscriptionPlan_should_return_200() throws Exception {
        when(createSubscriptionPlan.handle(any())).thenReturn(fixedUUID);
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/subscription-plan/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(new CreateSubscriptionPlanCommand(100d, Period.Yearly))));

        resultActions.andExpect(status().isOk())
                .andExpect(content().string("\"" + fixedUUID.toString() + "\""));
    }

    @Test
    void findById_should_return_200_with_json() throws Exception {
        when(getSubscriptionPlan.handle(new GetSubscriptionPlanCommand(fixedUUID.toString()))).thenReturn(java.util.Optional.of(SubscriptionPlan.createMonthly(new SubscriptionPlanId(fixedUUID), 100d)));
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/subscription-plan/" + fixedUUID.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(new CreateSubscriptionPlanCommand(100d, Period.Yearly))));

        resultActions.andExpect(status().isOk())
                .andExpect(content().json(
                        "{'id':{'uuid':'912eae98-15d7-4af0-8f8e-8c687c77a41b'},'basePrice':{'amount':100.0},'period':'Monthly','discountRate':{'rate':0.0},'totalPrice':{'value':100.0}}"
                ));
    }

    private String toJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMobjectMapper = new ObjectMapper();
        objectMobjectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = objectMobjectMapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(object);
    }
}

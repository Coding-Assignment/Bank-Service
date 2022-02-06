package com.coding.assignment.bankservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BankServiceApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    @Test
    public void createBankUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/bank-user")
                        .header("Authorization", "$2a$12$0e3RuqI2orV.jGs6ntWCRO5ywIS0gOOFyjkGQY0jFVjKPSbim8Zxy")
                        .content("{\n" +
                                "    \"password\":\"123456\",\n" +
                                "    \"fullName\":\"Yamam Alkhatib\",\n" +
                                "    \"username\":\"yamam1\"\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(status().isCreated())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.accessToken")
                        .isNotEmpty());
    }

}

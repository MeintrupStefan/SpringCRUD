package com.meintrup.springcrud.controllers.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class FriendsControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test if a request with a missing attribute returns 400.")
    void testGetFriendsMissingParam() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/friends/of")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Test if a request with an incorrect attribute returns 400.")
    void testGetFriendsIncorrectParam1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/friends/of?test=22")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Test if a request with an incorrect userId returns 400.")
    void testGetFriendsIncorrectParam2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/friends/of?userId=22")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
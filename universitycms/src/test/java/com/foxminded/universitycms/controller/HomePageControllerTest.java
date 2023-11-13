package com.foxminded.universitycms.controller;

import com.foxminded.universitycms.testconfiguration.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
@AutoConfigureMockMvc
class HomePageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(roles = "TEACHER", username = "first@mail.com")
    void getHomePageShouldReturnHomePageForUserWithRoleTeacher() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/main"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}

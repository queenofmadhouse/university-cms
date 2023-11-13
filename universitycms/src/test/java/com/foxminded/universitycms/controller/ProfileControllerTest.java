package com.foxminded.universitycms.controller;

import com.foxminded.universitycms.entity.Student;
import com.foxminded.universitycms.entity.Teacher;
import com.foxminded.universitycms.testconfiguration.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.instanceOf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
@AutoConfigureMockMvc
class ProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(roles = "TEACHER", username = "first@mail.com")
    void getProfileInfoShouldGetProfileInfoForTeacher() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/profile"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("profile", instanceOf(Teacher.class)));
    }

    @Test
    @WithMockUser(roles = "STUDENT", username = "second@mail.com")
    void getProfileInfoShouldGetProfileInfoForStudent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/profile"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("profile", instanceOf(Student.class)));
    }

}

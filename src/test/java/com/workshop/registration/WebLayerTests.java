package com.workshop.registration;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class WebLayerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void homePageListsCourses() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Course Registration Portal")))
                .andExpect(content().string(containsString("DevSecOps Foundations")));
    }

    @Test
    void registrationShowsConfirmation() throws Exception {
        mockMvc.perform(post("/register")
                        .param("firstName", "Test")
                        .param("lastName", "Student")
                        .param("email", "test.student@example.edu")
                        .param("courseCode", "DCK120"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Registration confirmed")))
                .andExpect(content().string(containsString("Docker and Containers")));
    }

    @Test
    void incompleteRegistrationShowsError() throws Exception {
        mockMvc.perform(post("/register").param("firstName", "OnlyFirstName"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Please fill in")));
    }

    @Test
    void coursesApiReturnsJson() throws Exception {
        mockMvc.perform(get("/api/courses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(5));
    }

    @Test
    void studentSearchFindsByFirstName() throws Exception {
        mockMvc.perform(get("/api/students/search").param("name", "Asha"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email").value("asha.rao@example.edu"));
    }

    @Test
    void adminStatsRequirePassword() throws Exception {
        mockMvc.perform(get("/api/admin/stats"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void healthEndpointIsUp() throws Exception {
        mockMvc.perform(get("/actuator/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UP"));
    }
}

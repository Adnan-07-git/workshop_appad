package com.workshop.registration;

import static org.assertj.core.api.Assertions.assertThat;

import com.workshop.registration.model.Course;
import com.workshop.registration.service.RegistrationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RegistrationServiceTests {

    @Autowired
    private RegistrationService registrationService;

    @Test
    void buildsConfirmationMessage() {
        Course course = new Course("DSO101", "DevSecOps Foundations", 2, 60);
        String message = registrationService.buildConfirmationMessage("Asha", course);
        assertThat(message)
                .isEqualTo("Hi Asha, you are registered for DevSecOps Foundations (DSO101). Duration: 2 day(s).");
    }
}

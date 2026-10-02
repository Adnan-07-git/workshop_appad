package com.workshop.registration.web;

import com.workshop.registration.repository.RegistrationRepository;
import com.workshop.registration.repository.StudentRepository;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Simple statistics for the admin office. Protected by a shared password header.
 */
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private static final String ADMIN_PASSWORD = "Admin@2026";

    private final StudentRepository studentRepository;
    private final RegistrationRepository registrationRepository;

    public AdminController(StudentRepository studentRepository, RegistrationRepository registrationRepository) {
        this.studentRepository = studentRepository;
        this.registrationRepository = registrationRepository;
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Long>> stats(
            @RequestHeader(value = "X-Admin-Password", required = false) String password) {
        if (!ADMIN_PASSWORD.equals(password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(Map.of(
                "students", studentRepository.count(),
                "registrations", registrationRepository.count()));
    }
}

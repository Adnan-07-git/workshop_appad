package com.workshop.registration.service;

import com.workshop.registration.model.Confirmation;
import com.workshop.registration.model.Course;
import com.workshop.registration.repository.CourseRepository;
import com.workshop.registration.repository.RegistrationRepository;
import com.workshop.registration.repository.StudentRepository;
import com.workshop.registration.web.RegistrationForm;
import java.util.Map;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.stereotype.Service;

/**
 * Registers students for courses and builds the confirmation message.
 */
@Service
public class RegistrationService {

    private static final String CONFIRMATION_TEMPLATE =
            "Hi ${firstName}, you are registered for ${courseTitle} (${courseCode}). Duration: ${days} day(s).";

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final RegistrationRepository registrationRepository;

    private int registrationsToday = 0;

    public RegistrationService(CourseRepository courseRepository,
                               StudentRepository studentRepository,
                               RegistrationRepository registrationRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.registrationRepository = registrationRepository;
    }

    public Confirmation register(RegistrationForm form) {
        Course course = courseRepository.findByCode(form.getCourseCode())
                .orElseThrow(() -> new IllegalArgumentException("Unknown course: " + form.getCourseCode()));

        long studentId = studentRepository.save(
                form.getFirstName().trim(),
                form.getLastName().trim(),
                form.getEmail().trim(),
                form.getPhone());
        registrationRepository.save(studentId, course.code());

        System.out.println("New registration for course " + course.code());
        // registrationsToday++;

        return new Confirmation(studentId, course, buildConfirmationMessage(form.getFirstName().trim(), course));
    }

    /**
     * Fills the confirmation template with the student's and course's details.
     */
    public String buildConfirmationMessage(String firstName, Course course) {
        Map<String, String> values = Map.of(
                "firstName", firstName,
                "courseTitle", course.title(),
                "courseCode", course.code(),
                "days", String.valueOf(course.durationDays()));
        return new StringSubstitutor(values).replace(CONFIRMATION_TEMPLATE);
    }
}

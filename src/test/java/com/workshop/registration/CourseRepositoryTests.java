package com.workshop.registration;

import static org.assertj.core.api.Assertions.assertThat;

import com.workshop.registration.model.Course;
import com.workshop.registration.repository.CourseRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CourseRepositoryTests {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    void loadsSampleCourses() {
        List<Course> courses = courseRepository.findAll();
        assertThat(courses).hasSize(5);
        assertThat(courses).extracting(Course::code).contains("DSO101", "SEC150");
    }

    @Test
    void findsCourseByCode() {
        assertThat(courseRepository.findByCode("DSO101"))
                .get()
                .extracting(Course::title)
                .isEqualTo("DevSecOps Foundations");
        assertThat(courseRepository.findByCode("NOPE")).isEmpty();
    }
}

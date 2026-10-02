package com.workshop.registration.web;

import com.workshop.registration.model.Course;
import com.workshop.registration.model.Student;
import com.workshop.registration.repository.CourseRepository;
import com.workshop.registration.repository.StudentRepository;
import com.workshop.registration.service.BadgeService;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * JSON API used by the front-office team.
 */
@RestController
@RequestMapping("/api")
public class StudentApiController {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final BadgeService badgeService;

    public StudentApiController(CourseRepository courseRepository,
                                StudentRepository studentRepository,
                                BadgeService badgeService) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.badgeService = badgeService;
    }

    @GetMapping("/courses")
    public List<Course> courses() {
        return courseRepository.findAll();
    }

    /**
     * Example: /api/students/search?name=Asha
     */
    @GetMapping("/students/search")
    public List<Student> search(@RequestParam("name") String name) {
        return studentRepository.searchByFirstName(name);
    }

    /**
     * Example: /api/badge?name=Asha
     */
    @GetMapping("/badge")
    public Map<String, String> badge(@RequestParam("name") String name) {
        return Map.of("name", name, "badge", badgeService.badgeFor(name));
    }
}

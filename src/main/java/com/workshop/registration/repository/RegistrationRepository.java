package com.workshop.registration.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Stores which student registered for which course.
 */
@Repository
public class RegistrationRepository {

    private final JdbcTemplate jdbcTemplate;

    public RegistrationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(long studentId, String courseCode) {
        jdbcTemplate.update(
                "INSERT INTO registrations (student_id, course_code) VALUES (?, ?)",
                studentId,
                courseCode);
    }

    public long count() {
        Long total = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM registrations", Long.class);
        return total == null ? 0 : total;
    }
}

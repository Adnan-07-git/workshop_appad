package com.workshop.registration.repository;

import com.workshop.registration.model.Course;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * Reads courses from the database.
 */
@Repository
public class CourseRepository {

    private static final RowMapper<Course> COURSE_MAPPER = (rs, rowNum) -> new Course(
            rs.getString("code"),
            rs.getString("title"),
            rs.getInt("duration_days"),
            rs.getInt("seats"));

    private final JdbcTemplate jdbcTemplate;

    public CourseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Course> findAll() {
        return jdbcTemplate.query(
                "SELECT code, title, duration_days, seats FROM courses ORDER BY code",
                COURSE_MAPPER);
    }

    public Optional<Course> findByCode(String code) {
        return jdbcTemplate.query(
                        "SELECT code, title, duration_days, seats FROM courses WHERE code = ?",
                        COURSE_MAPPER,
                        code)
                .stream()
                .findFirst();
    }
}

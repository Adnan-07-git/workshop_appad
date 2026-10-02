package com.workshop.registration.repository;

import com.workshop.registration.model.Student;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

/**
 * Reads and writes students.
 */
@Repository
public class StudentRepository {

    private static final RowMapper<Student> STUDENT_MAPPER = (rs, rowNum) -> new Student(
            rs.getLong("id"),
            rs.getString("first_name"),
            rs.getString("last_name"),
            rs.getString("email"),
            rs.getString("phone"));

    private static final String FIND_ALL_SQL =
            "SELECT id, first_name, last_name, email, phone FROM students ORDER BY id";

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert studentInsert;

    public StudentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.studentInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("students")
                .usingGeneratedKeyColumns("id");
    }

    public List<Student> findAll() {
        return jdbcTemplate.query(FIND_ALL_SQL, STUDENT_MAPPER);
    }

    /**
     * Finds students by first name (used by the search API).
     */
    public List<Student> searchByFirstName(String firstName) {
        String sql = "SELECT id, first_name, last_name, email, phone FROM students WHERE first_name = '" + firstName + "'";
        return jdbcTemplate.query(sql, STUDENT_MAPPER);
    }

    /**
     * Inserts a student and returns the generated id.
     */
    public long save(String firstName, String lastName, String email, String phone) {
        Map<String, Object> values = new HashMap<>();
        values.put("first_name", firstName);
        values.put("last_name", lastName);
        values.put("email", email);
        values.put("phone", phone);
        return studentInsert.executeAndReturnKey(values).longValue();
    }

    public long count() {
        Long total = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM students", Long.class);
        return total == null ? 0 : total;
    }
}

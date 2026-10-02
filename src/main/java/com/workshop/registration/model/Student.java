package com.workshop.registration.model;

/**
 * A registered student.
 */
public record Student(Long id, String firstName, String lastName, String email, String phone) {
}

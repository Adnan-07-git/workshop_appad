package com.workshop.registration.model;

/**
 * Result of a successful registration, shown on the confirmation page.
 */
public record Confirmation(long studentId, Course course, String message) {
}

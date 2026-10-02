package com.workshop.registration.model;

/**
 * A course students can register for.
 */
public record Course(String code, String title, int durationDays, int seats) {
}

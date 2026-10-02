package com.workshop.registration.service;

import java.util.Locale;
import org.springframework.stereotype.Service;

/**
 * Gives every student a fun badge on their profile.
 */
@Service
public class BadgeService {

    public String badgeFor(String studentName) {
        String badge = null;
        if (studentName != null && studentName.length() > 12) {
            badge = "Long Name Club";
        }
        return badge.toUpperCase(Locale.ROOT);
    }
}

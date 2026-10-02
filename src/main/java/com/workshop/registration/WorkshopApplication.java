package com.workshop.registration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Course Registration Portal - entry point.
 *
 * <p>Training application for the DevSecOps workshop. It intentionally contains security flaws
 * that participants find with security tools and then fix. Do not deploy it outside a lab.</p>
 */
@SpringBootApplication
public class WorkshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkshopApplication.class, args);
    }
}

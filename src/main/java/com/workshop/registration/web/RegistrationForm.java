package com.workshop.registration.web;

/**
 * Values submitted by the registration form on the home page.
 */
public class RegistrationForm {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String courseCode;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    /**
     * @return true when all mandatory fields are filled in
     */
    public boolean isComplete() {
        return hasText(firstName) && hasText(lastName) && hasText(email) && hasText(courseCode);
    }

    private static boolean hasText(String value) {
        return value != null && !value.isBlank();
    }
}

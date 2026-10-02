package com.workshop.registration.web;

import com.workshop.registration.model.Confirmation;
import com.workshop.registration.repository.CourseRepository;
import com.workshop.registration.service.CertificateStorageService;
import com.workshop.registration.service.RegistrationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * HTML pages: course list + registration form, and the confirmation page.
 */
@Controller
public class HomeController {

    private final CourseRepository courseRepository;
    private final RegistrationService registrationService;
    private final CertificateStorageService certificateStorageService;
    private final String appVersion;

    public HomeController(CourseRepository courseRepository,
                          RegistrationService registrationService,
                          CertificateStorageService certificateStorageService,
                          @Value("${app.version:dev}") String appVersion) {
        this.courseRepository = courseRepository;
        this.registrationService = registrationService;
        this.certificateStorageService = certificateStorageService;
        this.appVersion = appVersion;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("form", new RegistrationForm());
        addPageData(model);
        return "index";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("form") RegistrationForm form, Model model) {
        if (!form.isComplete()) {
            model.addAttribute("error", "Please fill in first name, last name, email and choose a course.");
            addPageData(model);
            return "index";
        }
        Confirmation confirmation = registrationService.register(form);
        model.addAttribute("confirmation", confirmation);
        model.addAttribute("appVersion", appVersion);
        return "confirmation";
    }

    private void addPageData(Model model) {
        model.addAttribute("courses", courseRepository.findAll());
        model.addAttribute("storageConfigured", certificateStorageService.isConfigured());
        model.addAttribute("appVersion", appVersion);
    }
}

package com.project.bilbioteka.App.registration;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping(path = "/registration")
    public String registerUserAccount(
            @ModelAttribute("user") RegistrationRequest registrationRequest,
            HttpServletRequest request,
            Errors errors) {

        registrationService.register(registrationRequest);
        return "home";
    }

    @GetMapping(path = "/registration")
    public String showRegistrationForm(WebRequest request, Model model) {
        RegistrationRequest registrationRequest = new RegistrationRequest("name", "email@gmail.com", "pass");
        model.addAttribute("user", registrationRequest);
        return "signup";
    }

}

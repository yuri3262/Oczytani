package com.project.bilbioteka.App.registration;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@AllArgsConstructor
@RequestMapping(path="/registration")
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public String registerUserAccount(
            @ModelAttribute("user") RegistrationRequest registrationRequest,
            HttpServletRequest request,
            Errors errors) {
        registrationService.register(registrationRequest);
        return "home";
    }

    @GetMapping
    public String showRegistrationForm(WebRequest request, Model model) {
        RegistrationRequest registrationRequest = new RegistrationRequest("", "", "");
        model.addAttribute("user", registrationRequest);
        return "signup";
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }



}

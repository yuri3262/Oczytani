package com.project.bilbioteka.App.home;

import com.project.bilbioteka.App.registration.RegistrationRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homepage(Model model) {
        return "home";
    }

    @GetMapping("/login")
    public String viewLoginPage() {
        // custom logic before showing login page...

        return "login";
    }

    @RequestMapping("/home_after_login")
    public String afterLogin() {
        return "home_after_login";
    }
}

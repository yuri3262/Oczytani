package com.project.bilbioteka.App.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}

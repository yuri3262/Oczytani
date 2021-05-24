package com.project.bilbioteka.App.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/error")
    public String viewErrorPage() {

        return "error";
    }
    @GetMapping("/error/stack")
    public void viewErrorStackPage() {
        Thread.dumpStack();
    }

}

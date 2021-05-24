package com.project.bilbioteka.App.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.*;
import java.io.FileNotFoundException;

@Controller
public class ErrorController {

    @GetMapping("/error")
    public String viewErrorPage() {

        return "error";
    }
    @GetMapping("/error/stack")
    public String viewErrorStackPage() {
        // File file = new File("test.log");
        try
        {
            PrintStream pw = new PrintStream(new FileOutputStream("src/main/resources/templates/error_stack.html"));
            Throwable t = new Throwable();
            t.printStackTrace(pw);
            
        }catch (FileNotFoundException ex)  
        {
            ex.printStackTrace();
        }
        return "error_stack";
        
    }

}

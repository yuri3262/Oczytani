package com.project.bilbioteka.App.controllers;

import com.project.bilbioteka.App.user.AppUser;
import com.project.bilbioteka.App.user.AppUserService;
import com.project.bilbioteka.App.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class WorkerPanelController {

    @Autowired
    private AppUserService userService;

    @GetMapping("/worker/users")
    public String users(Model model) {
        List<AppUser> users = userService.getAllUsers();

        // do not display admin and worker users
        for(int i = 0; i < users.size(); i++) {
            if(users.get(i).getRole() == UserRole.ADMIN || users.get(i).getRole() == UserRole.WORKER)
                users.remove(i);
        }

        model.addAttribute("users", users);
        return "manage_users_panel";
    }

    @GetMapping("/worker/books")
    public String books(Model model) {
        /*List<AppUser> books = userService.getAllUsers();

        // do not display admin and worker users
        for(int i = 0; i < users.size(); i++) {
            if(users.get(i).getRole() == UserRole.ADMIN || users.get(i).getRole() == UserRole.WORKER)
                users.remove(i);
        }

        model.addAttribute("users", users);*/
        return "manage_books_panel";
    }

}

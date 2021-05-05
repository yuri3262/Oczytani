package com.project.bilbioteka.App.controllers;

import com.project.bilbioteka.App.user.AppUser;
import com.project.bilbioteka.App.user.AppUserService;
import com.project.bilbioteka.App.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminPanelController {

    @Autowired
    private AppUserService userService;

    @GetMapping("/admin/users")
    public String homepage(Model model) {
        List<AppUser> users = userService.getAllUsers();

        // do not display admin users
        for(int i = 0; i < users.size(); i++) {
            if(users.get(i).getRole() == UserRole.ADMIN)
                users.remove(i);
        }

        model.addAttribute("users", users);
        return "admin_panel";
    }

}

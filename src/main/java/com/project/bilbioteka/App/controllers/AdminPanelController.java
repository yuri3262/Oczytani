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

import java.util.List;

@Controller
public class AdminPanelController {

    @Autowired
    private AppUserService userService;

    @GetMapping("/admin/users")
    public String users(Model model) {
        List<AppUser> users = userService.getAllUsers();

        // do not display admin users
        for(int i = 0; i < users.size(); i++) {
            if(users.get(i).getRole() == UserRole.ADMIN)
                users.remove(i);
        }

        model.addAttribute("users", users);
        return "manage_users_panel";
    }


    @GetMapping("/admin/edit/{id}")
    public String updateUserForm(@PathVariable String id, Model model) {
        AppUser user = userService.getUser(id);
        model.addAttribute("user", user);
        return "update_user";
    }

    @PostMapping("/admin/update/{id}")
    public String updateUser(@ModelAttribute("user") AppUser user, @PathVariable String id) {
        userService.updateUser(user, id);
        return "redirect:/admin/users";
    }

    @PostMapping("/admin/delete/{id}")
    public String deleteUser(@ModelAttribute("user") AppUser user, @PathVariable Long id) {
        if(userService.loadUserById(id) != null){
            userService.deleteUserById(id);
        }
        return "redirect:/admin/users";
    }




}

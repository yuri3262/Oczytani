package com.project.bilbioteka.App.controllers;

import com.project.bilbioteka.App.user.AppUser;
import com.project.bilbioteka.App.user.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;


@Controller
@AllArgsConstructor
public class PasswordController {

    @Autowired
    private final AppUserService userService;

    @GetMapping("/user/updatePassword")
    public String changeUserPassword() {
        return "change_password.html";
    }

    @PostMapping("/user/updatePassword")
    @PreAuthorize("hasRole('USER')")
    public String changeUserPassword(HttpServletRequest request, Model model,
                                        @RequestParam("oldpassword") String oldPassword,
                                        @RequestParam("password1") String password1,
                                        @RequestParam("password2") String password2) throws ServletException {


        AppUser user = userService.findAppUserByName(
                SecurityContextHolder.getContext().getAuthentication().getName());


        if (!(password1.equals(password2))) {
            model.addAttribute("message", "Your new password and confirmation password does not match.");

            return "change_password";
        }

        if (oldPassword.equals(password1)) {
            model.addAttribute("message", "Your new password must be different than the old one.");

            return "change_password";
        }

        if (!userService.checkIfValidOldPassword(user, oldPassword)) {
            model.addAttribute("message", "Your old password is incorrect.");
            return "change_password";

        } else {
            userService.changeUserPassword(user, password1);
            request.logout();
            model.addAttribute("message", "You have changed your password successfully. "
                    + "Please login again.");
            return "login";
        }

    }
}

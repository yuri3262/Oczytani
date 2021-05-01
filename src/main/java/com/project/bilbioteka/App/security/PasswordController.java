package com.project.bilbioteka.App.security;

import com.project.bilbioteka.App.user.AppUser;
import com.project.bilbioteka.App.user.AppUserRepository;
import com.project.bilbioteka.App.user.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class PasswordController {

    @Autowired
    private final AppUserService userService;

    @PostMapping("/user/updatePassword")
    @PreAuthorize("hasRole('USER')")
    public String changeUserPassword(@RequestParam("oldpassword") String oldPassword,
                                     @RequestParam("password1") String password) {
        AppUser user = userService.findAppUserByName(
                SecurityContextHolder.getContext().getAuthentication().getName());

        if (!userService.checkIfValidOldPassword(user, oldPassword)) {
            throw new InvalidOldPasswordException();
        }

        userService.changeUserPassword(user, password);
        return "password_changed";
    }

    @GetMapping("/user/updatePassword")
    public String changeUserPassword(){
        return "change_password.html";
    }

}

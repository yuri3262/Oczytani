package com.project.bilbioteka.App.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AppUserController {
    @Autowired
    private MessageSource messages;
    @Autowired
    private AppUserRepository userRepository;

    @Autowired
    private AppUserService userService;
    @GetMapping("/users")
    public List<AppUser> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public AppUser getUser(@PathVariable String id) {
        return userService.getUser(id);
    }

    @PostMapping("/users")
    public void addUser(@RequestBody AppUser user) {
        userService.addUser(user);
    }

    @PutMapping("/users/{id}")
    public void updateUser(@RequestBody AppUser user, @PathVariable String id) {
        userService.updateUser(user, id);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }




}

package com.project.bilbioteka.App.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AppUserController {

    @Autowired
    private AppUserRepository userRepository;

    @GetMapping("/users")
    public List<AppUser> getAllUsers() {
        List<AppUser> users = new ArrayList<>();
        userRepository.findAll()
                .forEach(users::add);

        return users;
    }

    @GetMapping("/users/{id}")
    public AppUser getUser(@PathVariable String id) {
        return userRepository.findById(Long.parseLong(id));
    }

    @PostMapping("/users")
    public void addUser(@RequestBody AppUser user) {
        userRepository.save(user);
    }

    @PutMapping("/users/{id}")
    public void updateUser(@RequestBody AppUser user, @PathVariable String id) {
        AppUser updatedUser = userRepository.findById(Long.parseLong(id));
        updatedUser.setUserName(user.getName());
        updatedUser.setPassword(user.getPassword());
        userRepository.save(updatedUser);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable String id) {
        userRepository.deleteById(Long.parseLong(id));
    }
}

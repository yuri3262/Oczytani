package com.project.bilbioteka.App.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll()
                .forEach(users::add);

        return users;
    }

    public User getUser(String id) {
        return userRepository.findById(Long.parseLong(id));
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(String id, User user) {
        User updatedUser = userRepository.findById(Long.parseLong(id));
        updatedUser.setName(user.getName());
        updatedUser.setPassword(user.getPassword());
        userRepository.save(updatedUser);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(Long.parseLong(id));
    }

}

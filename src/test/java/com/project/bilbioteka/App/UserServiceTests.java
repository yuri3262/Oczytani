package com.project.bilbioteka.App;

import com.project.bilbioteka.App.user.User;
import com.project.bilbioteka.App.user.UserRepository;
import com.project.bilbioteka.App.user.UserRole;
import com.project.bilbioteka.App.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class UserServiceTests {

    @Resource
    private UserService userService;

    @Test
    public void givenUser_signUpUser_thenGetOk() {
        User user = new User("john", "john@doe.com", "password", UserRole.USER );

        assertEquals("it works", userService.signUpUser(user));
    }


}

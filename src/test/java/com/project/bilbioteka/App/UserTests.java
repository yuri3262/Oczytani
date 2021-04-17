package com.project.bilbioteka.App;

import com.project.bilbioteka.App.user.AppUser;
import com.project.bilbioteka.App.user.AppUserRepository;
import com.project.bilbioteka.App.user.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserTests {

    @Resource
    private AppUserRepository userRepository;

    @Test
    public void givenUser_whenSave_thenGetOk() {
        AppUser user = new AppUser("john","johny@doe.com" , "password", UserRole.USER );
        userRepository.save(user);

        Optional<AppUser> optuser2 = userRepository.findByEmail("johny@doe.com");
        assertEquals(true,optuser2.isPresent());
        AppUser user2 = optuser2.get();
        assertEquals("john", user2.getName());
        assertEquals("password", user2.getPassword());
        assertEquals("johny@doe.com", user2.getEmail());
        assertEquals(UserRole.USER, user2.getRole());
    }
    @Test
    public void givenUser_whenUpdate_thenGetOk() {
        AppUser user = new AppUser("john", "johny@doe.com", "password", UserRole.USER );
        user.setPassword("password2");
        user.setUserName("john2");

        assertEquals("john2", user.getName());
        assertEquals("password2", user.getPassword());
        assertEquals("johny@doe.com", user.getEmail());
        assertEquals(UserRole.USER, user.getRole());
    }


}


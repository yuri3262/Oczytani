package com.project.bilbioteka.App.unit;

import com.project.bilbioteka.App.user.AppUser;
import com.project.bilbioteka.App.user.AppUserService;
import com.project.bilbioteka.App.user.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AppUserServiceTests {

    @Resource
    private AppUserService userService;

    @Test
    public void givenUser_signUpUser_thenGetOk() {
        AppUser user = new AppUser("john", "john@doe.com", "password", UserRole.USER );

        String token = userService.signUpUser(user);
        AppUser user2 = userService.findAppUserByEmail(user.getEmail());
        assertNotEquals("password", user2.getPassword());
        assertFalse(user2.isEnabled());

        userService.enableUser(user.getEmail());

        AppUser user3 = userService.findAppUserByEmail(user.getEmail());
        assertTrue(user3.isEnabled());
    }


}

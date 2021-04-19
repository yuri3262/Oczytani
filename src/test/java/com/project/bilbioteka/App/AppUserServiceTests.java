package com.project.bilbioteka.App;

import com.project.bilbioteka.App.user.AppUser;
import com.project.bilbioteka.App.user.UserRole;
import com.project.bilbioteka.App.user.AppUserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class AppUserServiceTests {

    @Resource
    private AppUserService userService;

    @Test
    public void givenUser_signUpUser_thenGetOk() {
        AppUser user = new AppUser("john", "john@doe.com", "password", UserRole.USER );

        String token = userService.signUpUser(user);
    }


}

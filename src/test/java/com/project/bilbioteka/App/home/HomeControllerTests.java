package com.project.bilbioteka.App.home;

import com.project.bilbioteka.App.registration.RegistrationRequest;
import com.project.bilbioteka.App.registration.RegistrationService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.springframework.ui.Model;

@SpringBootTest
class HomeControllerTests {

    @Test
    public void givenRequest_whenHome_thenGetOk() {

        HomeController homeController = new HomeController();

        assertEquals( "login", homeController.viewLoginPage() );
        assertEquals( "home_after_login", homeController.afterLogin() );


    }


}


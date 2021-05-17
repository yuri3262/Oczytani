package com.project.bilbioteka.App.unit;

import com.project.bilbioteka.App.home.HomeController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class HomeControllerTests {

    @Test
    public void givenRequest_whenHome_thenGetOk() {

        HomeController homeController = new HomeController();

        assertEquals( "login", homeController.viewLoginPage() );
        assertEquals( "home_after_login", homeController.afterLogin() );


    }


}


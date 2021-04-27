package com.project.bilbioteka.App.e2e;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertEquals;

@SpringBootTest
class HomeE2ETests {

    private URL driverURL;
    {
        try {
            driverURL = new URL("http://localhost:9515");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private WebDriver driver = new RemoteWebDriver(driverURL, DesiredCapabilities.chrome());

    @Test
    public void shouldBeAbleEnterUserNameAndClickSubmitToVerifyWelcomeMessage()
    {
        driver.get("https://oczytani.azurewebsites.net/");

        assertEquals("Welcome!", driver.getTitle());

    }


}


package com.project.bilbioteka.App.e2e;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertEquals;

@SpringBootTest
class HomeE2ETests {

    private final String testURL = "https://oczytani.azurewebsites.net";

    private URL driverURL;
    {
        try {
            driverURL = new URL("http://localhost:9515/wd/hub"); ///wd/hub
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private WebDriver driver = new RemoteWebDriver(driverURL, DesiredCapabilities.chrome());

    @Test
    public void pageElements()
    {
        driver.get(testURL);
        assertEquals("Welcome!", driver.getTitle());

        WebElement signUpLink = driver.findElement(new By.ByLinkText("SignUp"));
        signUpLink.click();
        assertEquals(testURL + "/registration", driver.getCurrentUrl());
        driver.navigate().back();

        WebElement logInLink = driver.findElement(new By.ByLinkText("LogIn"));
        logInLink.click();
        assertEquals(testURL + "/login", driver.getCurrentUrl());
        driver.navigate().back();

        WebElement logoHomeLink = driver.findElement(By.id("nav_logo"));
        logoHomeLink.click();
        assertEquals(testURL + "/" , driver.getCurrentUrl());
        driver.navigate().back();

    }


}


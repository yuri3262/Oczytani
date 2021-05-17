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
class RegistrationE2ETests {

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
        driver.get(testURL + "/registration");
        assertEquals("Registration", driver.getTitle());

        WebElement nameInput = driver.findElement(By.id("name"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement emailInput = driver.findElement(By.id("exampleInputEmail1"));
        WebElement submitButton = driver.findElement(By.cssSelector("button"));

        WebElement logInLink = driver.findElement(new By.ByLinkText("Log In"));
        logInLink.click();
        assertEquals(testURL + "/login", driver.getCurrentUrl());
        driver.navigate().back();

        WebElement logoHomeLink = driver.findElement(new By.ByLinkText("Homepage"));
        logoHomeLink.click();
        assertEquals(testURL + "/" , driver.getCurrentUrl());
        driver.navigate().back();

    }

    @Test
    public void registerWithEmptyData()
    {
        driver.get(testURL + "/registration");
        assertEquals("Registration", driver.getTitle());

        WebElement submitButton = driver.findElement(By.cssSelector("button"));

        submitButton.click();
        String pageHeader = driver.findElement(By.tagName("h1")).getText();
        assertEquals("Whitelabel Error Page", pageHeader);
    }

    @Test
    public void registerWithCorrectData()
    {
        driver.get(testURL + "/registration");
        assertEquals("Registration", driver.getTitle());

        WebElement nameInput = driver.findElement(By.id("name"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement emailInput = driver.findElement(By.id("exampleInputEmail1"));
        WebElement submitButton = driver.findElement(By.cssSelector("button"));

        nameInput.sendKeys("testUserFromSelenium");
        passwordInput.sendKeys("testPassword");
        emailInput.sendKeys("testmailSelenium@gmail.com");
        submitButton.click();


    }

    @Test
    public void loginWithRegisteredData()
    {
        driver.get(testURL + "/login");
        assertEquals("Sign In", driver.getTitle());

        WebElement emailInput = driver.findElement(By.name("email"));
        WebElement passwordInput = driver.findElement(By.name("password"));
        WebElement submitButton = driver.findElement(By.cssSelector("button"));

        passwordInput.sendKeys("testPassword");
        emailInput.sendKeys("testmailSelenium@gmail.com");
        submitButton.click();
    }


}


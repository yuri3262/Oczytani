package com.project.bilbioteka.App.e2e;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.interactions.Actions;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertEquals;

@SpringBootTest
class AdminE2ETests {

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
    public void loginAsAdmin()
    {
        driver.get(testURL + "/login");

        WebElement emailInput = driver.findElement(By.name("email"));
        WebElement passwordInput = driver.findElement(By.name("password"));
        WebElement submitButton = driver.findElement(By.cssSelector("button"));

        passwordInput.sendKeys("admin");
        emailInput.sendKeys("admin@admin.com");
        submitButton.click();

        assertEquals("Welcome!", driver.getTitle());

        WebElement usersLink = driver.findElement(new By.ByLinkText("users"));
        usersLink.click();
        assertEquals("User management panel", driver.getTitle());

        WebElement booksLink = driver.findElement(new By.ByLinkText("books"));
        booksLink.click();
        assertEquals("Book management panel", driver.getTitle());
        WebElement addBookButton = driver.findElement(By.xpath("//input[@value='Add book']"));
        addBookButton.click();
        assertEquals(testURL + "/newBook", driver.getCurrentUrl());
        WebElement titleInput = driver.findElement(By.name("title"));
        WebElement authorInput = driver.findElement(By.name("author"));
        WebElement publisherInput = driver.findElement(By.name("publisher"));
        WebElement numberOfPagesInput = driver.findElement(By.name("numberOfPages"));
        WebElement category1Input = driver.findElement(By.id("category1"));
        WebElement dateBox = driver.findElement(By.xpath("//input[@name='dateOfPublication']"));
        WebElement availableInput = driver.findElement(By.id("isAvailable1"));
        WebElement addButton = driver.findElement(By.xpath("//form//input[@value='Add book']"));

        titleInput.sendKeys("Test Book");
        authorInput.sendKeys("Test Author");
        publisherInput.sendKeys("Test Publisher");
        numberOfPagesInput.sendKeys("4020");
        category1Input.click();
        dateBox.sendKeys("09252013");
        Actions actions = new Actions(driver);
        actions.moveToElement(addButton).click().perform();

        WebElement goBackLink = driver.findElement(new By.ByLinkText("Go back"));
        actions.moveToElement(goBackLink).click().perform();
    }
}


package com.example.QuickDine.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ReservationUITest {

    private WebDriver driver;

    @BeforeAll
    public void setupDriver() {
        WebDriverManager.firefoxdriver().setup(); // Auto-downloads compatible FirefoxDriver
    }

    @BeforeEach
    public void setup() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless"); // CI-friendly
        driver = new FirefoxDriver(options);
        driver.get("http://localhost:3000"); // Adjust if needed
    }

    @Test
    public void testReservationSuccess() {
        driver.findElement(By.id("customerName")).sendKeys("Senuja");
        driver.findElement(By.id("contactNumber")).sendKeys("0771234567");
        driver.findElement(By.id("reservationTime")).sendKeys("2025-09-01T19:00");
        driver.findElement(By.id("tableNumber")).sendKeys("5");
        driver.findElement(By.id("submitBtn")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("successMessage")));

        Assertions.assertEquals("Reservation successful!", successMsg.getText());
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
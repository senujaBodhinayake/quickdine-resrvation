package com.example.QuickDine.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class ReservationUITest {

    private WebDriver driver;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        driver.get("http://localhost:3000");
    }

    @Test
    void shouldSubmitReservationSuccessfully() {
        driver.findElement(By.id("customerName")).sendKeys("Senuja");
        driver.findElement(By.id("contactNumber")).sendKeys("0766970855");
        driver.findElement(By.id("reservationTime")).sendKeys("2025-08-29T19:15");
        driver.findElement(By.id("tableNumber")).sendKeys("6");
        driver.findElement(By.id("submitBtn")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement successMsg = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("successMessage"))
        );

        Assertions.assertTrue(successMsg.isDisplayed());
    }

    @Test
    void shouldRejectDuplicateReservation() {
        // First reservation
        driver.findElement(By.id("customerName")).sendKeys("Thamindu");
        driver.findElement(By.id("contactNumber")).sendKeys("0704545454");
        driver.findElement(By.id("reservationTime")).sendKeys("2025-08-29T19:00");
        driver.findElement(By.id("tableNumber")).sendKeys("5");
        driver.findElement(By.id("submitBtn")).click();

        // Second reservation (duplicate)
        driver.findElement(By.id("customerName")).clear();
        driver.findElement(By.id("customerName")).sendKeys("Gimhani");
        driver.findElement(By.id("submitBtn")).click();

        WebElement errorMsg = driver.findElement(By.id("errorMessage"));
        Assertions.assertTrue(errorMsg.isDisplayed());


    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

}

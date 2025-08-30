package com.example.QuickDine.ui;



import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ReservationUITest {

    private WebDriver driver;

    @BeforeAll
    public  void setupDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver-win64\\chromedriver.exe");



    }
    @BeforeEach
    public void startBrowser() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

//        options.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage");

        driver = new ChromeDriver(options);
        driver.get("http://localhost:3000");
    }
    @Test
    public void testReservationSuccess() {
        driver.findElement(By.id("customerName")).sendKeys("Senuja");
        driver.findElement(By.id("contactNumber")).sendKeys("0771234567");


        driver.findElement(By.id("tableNumber")).sendKeys("5");
        driver.findElement(By.id("submitBtn")).click();


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        WebElement msg = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("successMessage"))
        );
        Assertions.assertEquals("Reservation successful!",msg.getText());
    }
    @Test
    public void testPreventDoubleBooking() {
        String reservationTime = LocalDateTime.now().plusDays(1).withHour(19).withMinute(0)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));

        // First reservation attempt
        driver.findElement(By.id("customerName")).sendKeys("Thamindu");
        driver.findElement(By.id("contactNumber")).sendKeys("0771234567");
//        driver.findElement(By.id("reservationTime")).sendKeys(reservationTime);
        driver.findElement(By.id("tableNumber")).sendKeys("6");
        driver.findElement(By.id("submitBtn")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("successMessage")));
        Assertions.assertEquals("Reservation successful!", successMsg.getText());

        // Refresh page for second attempt
        driver.navigate().refresh();

        // Second reservation attempt with same table
        driver.findElement(By.id("customerName")).sendKeys("AnotherUser");
        driver.findElement(By.id("contactNumber")).sendKeys("0779999999");
//        driver.findElement(By.id("reservationTime")).sendKeys(reservationTime);
        driver.findElement(By.id("tableNumber")).sendKeys("6");
        driver.findElement(By.id("submitBtn")).click();

        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("errorMessage")));
        Assertions.assertTrue(errorMsg.getText().contains("Table already booked"));
    }
    @AfterEach
    public void closeBrowser() {
        if (driver!=null){
            driver.quit();
        }
    }

}

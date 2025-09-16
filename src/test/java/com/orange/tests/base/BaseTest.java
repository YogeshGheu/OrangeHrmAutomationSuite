package com.orange.tests.base;

import com.orange.pages.base.BasePage;
import com.orange.utils.BaseUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {

    private final ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    @BeforeMethod
    public void initDriver(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless=new");
        chromeOptions.addArguments("--window-size=1920,1080");

        WebDriver driverInstance = new ChromeDriver(chromeOptions);
        driverInstance.manage().window().maximize();
        driverInstance.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20L));
        String baseUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
        driverInstance.get(baseUrl);
        driver.set(driverInstance);
    }

    public WebDriver getDriver(){
        return driver.get();
    }

    @AfterMethod
    public void tearDown(){
        if(driver.get() != null){
            driver.get().quit();
            driver.remove();
        }
    }
}

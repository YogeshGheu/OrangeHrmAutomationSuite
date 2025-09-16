package com.orange.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtility extends BaseUtility{

    public WaitUtility(WebDriver driver){
        super(driver);
    }

    public void waitForElementToBeVisible(By locator){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
                wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public void waitForElementToBeVisible(By locator, int seconds){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
                wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}

package com.orange.pages.base;

import com.orange.utils.WaitUtility;
import org.openqa.selenium.*;

public class BasePage {

    protected WebDriver driver;
    WaitUtility waitUtility;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        waitUtility = new WaitUtility(driver);
    }

    protected WebElement find(By locator) {
        waitUtility.waitForElementToBeVisible(locator);
        return driver.findElement(locator);
    }

    protected void click(By locator) {
        waitUtility.waitForElementToBeVisible(locator);
        WebElement element = find(locator);
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    protected void sendText(By locator, String text) {
        WebElement element = find(locator);
        element.clear();
        element.sendKeys(text);
    }

}

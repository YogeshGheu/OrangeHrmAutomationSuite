package com.orange.utils;

import org.openqa.selenium.WebDriver;

public class BaseUtility {
    protected WebDriver driver;

    public BaseUtility(WebDriver driver){
        this.driver = driver;
    }
}

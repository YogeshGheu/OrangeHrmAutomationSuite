package com.orange.pages.application.dashboard;

import com.orange.pages.application.login.LoginPage;
import com.orange.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage {
    private final By dashboardHeading = By.xpath("//h6[text()=\"Dashboard\"]");

    public DashboardPage(WebDriver driver){
        super(driver);
    }

    public boolean isDashboardHeadingVisible(){
        return find(dashboardHeading).isDisplayed();
    }



}

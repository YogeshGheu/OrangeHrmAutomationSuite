package com.orange.tests.login;

import com.orange.pages.application.dashboard.DashboardPage;
import com.orange.pages.application.login.LoginPage;
import com.orange.tests.base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private final String username = System.getenv("ORANGEHRM_USER");
    private final String password = System.getenv("ORANGEHRM_PASSWORD");

    @Test(description = "test login functionality with valid credentials")
    public void testLoginWithValidCredentials(){
        WebDriver driver = getDriver();
        System.out.println(username + password);
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = loginPage.loginToApp(username, password);
        Assert.assertTrue(dashboardPage.isDashboardHeadingVisible());
    }

    @Test(description = "test login functionality with invalid credentials")
    public void testLoginWithInvalidCredentials(){
        WebDriver driver = getDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginToApp(username, "invalid");
        Assert.assertTrue(loginPage.isInvalidCredentialsLabelDisplayed());
    }
    @Test(description = "test login functionality with blank username and blank password")
    public void testLoginWithBlankCredentials(){
        WebDriver driver = getDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginToApp("", "");
        Assert.assertTrue(loginPage.isUsernameRequiredLabelDisplayed());
        Assert.assertTrue(loginPage.isPasswordRequiredLabelDisplayed());
    }

    @Test(description = "test login functionality with username and blank password")
    public void testLoginWithUserNameAndBlankPassword(){
        WebDriver driver = getDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginToApp(username, "");
        Assert.assertTrue(loginPage.isPasswordRequiredLabelDisplayed());
    }
}

package com.orange.pages.application.login;

import com.orange.pages.application.dashboard.DashboardPage;
import com.orange.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By usernameField = By.name("username");
    private final By passwordField = By.name("password");
    private final By submitButton = By.xpath("//button[@type=\"submit\"]");
    private final By usernameRequiredLabel = By.xpath("//span[text()=\"Required\" and preceding-sibling::div/descendant::label[text()=\"Username\"]]");
    private final By passwordRequiredLabel = By.xpath("//span[text()=\"Required\" and preceding-sibling::div/descendant::label[text()=\"Password\"]]");
    private final By invalidCredentialsLabel = By.xpath("//p[text()=\"Invalid credentials\"]");
    public LoginPage(WebDriver driver){
        super(driver);
    }

    private void enterUsername(String username){
        sendText(usernameField, username);
    }

    private void enterPassword(String password){
        sendText(passwordField, password);
    }

    private DashboardPage clickOnLoginButton(){
        click(submitButton);
        return new DashboardPage(driver);
    }

    public DashboardPage loginToApp(String username, String password){
        enterUsername(username);
        enterPassword(password);
        return clickOnLoginButton();
    }

    public boolean isUsernameRequiredLabelDisplayed(){
        return find(usernameRequiredLabel).isDisplayed();
    }

    public boolean isPasswordRequiredLabelDisplayed(){
        return find(passwordRequiredLabel).isDisplayed();
    }

    public boolean isInvalidCredentialsLabelDisplayed(){
        return find(invalidCredentialsLabel).isDisplayed();
    }


}

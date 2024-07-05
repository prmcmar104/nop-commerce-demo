package com.nopcommerce.demo.pages;

import com.aventstack.extentreports.Status;
import com.nopcommerce.demo.customlisteners.CustomListeners;
import com.nopcommerce.demo.utility.Utility;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

/**
 * Created by Jay Vaghani
 */
public class LoginPage extends Utility {

    private static final Logger log = LogManager.getLogger(HomePage.class);

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    @CacheLookup
    @FindBy(xpath = "//h1[contains(text(),'Welcome, Please Sign In!')]")
    WebElement welcomeText;

    @CacheLookup
    @FindBy(id = "Email")
    WebElement emailField;

    @CacheLookup
    @FindBy(name = "Password")
    WebElement passwordField;

    @CacheLookup
    @FindBy(xpath = "//button[contains(text(),'Log in')]")
    WebElement loginButton;

    @CacheLookup
    @FindBy(xpath = "//div[@class='message-error validation-summary-errors']")
    WebElement errorMessage;

    public String getWelcomeText() {
        String message = getTextFromElement(welcomeText);
        CustomListeners.node.log(Status.PASS, "Get welcomeText : " + welcomeText.getText());
        Reporter.log("Getting welcome text " + welcomeText.getText() + "<br>");
        log.info("Get welcomeText : " + welcomeText.getText());
        return message;
    }

    public void enterEmailId(String email) {
        sendTextToElement(emailField, email);
        CustomListeners.node.log(Status.PASS, "Enter EmailId '" + email + "' to email field : "
                + emailField.getText());
        Reporter.log("Enter email " + email + " to email field " + emailField.getText() + "<br>");
        log.info("Enter EmailId '" + email + "' to email field : "
                + emailField.getText());
    }

    public void enterPassword(String password) {
        sendTextToElement(passwordField, password);
        CustomListeners.node.log(Status.PASS, "Enter Password '" + password + "' to password field : "
                + passwordField.getText());
        Reporter.log("Enter password " + password + " to password field " + passwordField.getText() + "<br>");
        log.info("Enter Password '" + password + "' to password field : "
                + passwordField.getText());
    }

    public void clickOnLoginButton() {
        clickOnElement(loginButton);
        CustomListeners.node.log(Status.PASS, "Click on loginButton");
        Reporter.log("Clicking on Login Button <br>");
        log.info("Click on loginButton");
    }

    public String getErrorMessage() {
        String message = getTextFromElement(errorMessage);
        CustomListeners.node.log(Status.PASS, "Get errorMessage : " + errorMessage.getText());
        Reporter.log("Getting error message : " + errorMessage.getText() + "<br>");
        log.info("Get errorMessage : " + errorMessage.getText());
        return message;
    }
}

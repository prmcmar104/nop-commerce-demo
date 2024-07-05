package com.nopcommerce.demo.testsuite;

import com.nopcommerce.demo.pages.HomePage;
import com.nopcommerce.demo.pages.RegisterPage;
import com.nopcommerce.demo.testbase.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * Created by Jay Vaghani
 */

public class RegisterPageTest extends TestBase {

    HomePage homePage;
    RegisterPage registerPage;
    public static String email = getRandomString(5) + "@gmail.com";

    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        homePage = new HomePage();
        registerPage = new RegisterPage();
    }

    @Test(priority = 1, groups = {"smoke", "regression"})
    public void verifyUserShouldNavigateToRegisterPageSuccessfully() {
        homePage.clickOnRegisterLink();
        Assert.assertEquals("Register", registerPage.getRegisterText(), "Register page not displayed");
    }

    @Test(priority = 2, groups = {"sanity", "regression"})
    public void verifyThatFirstNameLastNameEmailPasswordAndConfirmPasswordFieldsAreMandatory() {
        SoftAssert softAssert = new SoftAssert();
        homePage.clickOnRegisterLink();
        registerPage.clickOnRegisterButton();
        softAssert.assertEquals("First name is required.",
                registerPage.getValidationErrorMessageForField("FirstName"),
                "FirstName field error message not displayed");
        softAssert.assertEquals("Last name is required.",
                registerPage.getValidationErrorMessageForField("LastName"),
                "LastName field error message not displayed");
        softAssert.assertEquals("Email is required.",
                registerPage.getValidationErrorMessageForField("Email"),
                "Email field error message not displayed");
        softAssert.assertEquals("Password is required.",
                registerPage.getValidationErrorMessageForField("Password"),
                "Password field error message not displayed");
        softAssert.assertEquals("Password is required.",
                registerPage.getValidationErrorMessageForField("ConfirmPassword"),
                "ConfirmPassword field error message not displayed");
        softAssert.assertAll();
    }

    @Test(priority = 3, groups = {"regression"})
    public void verifyThatUserShouldCreateAccountSuccessfully() {
        homePage.clickOnRegisterLink();
        registerPage.selectGender("Male");
        registerPage.enterFirstName("prime");
        registerPage.enterLastName("test");
        registerPage.selectDateOfBirth("22", "February", "1980");
        registerPage.enterEmail(email);
        registerPage.enterPassword("Prime123");
        registerPage.enterConfirmPassword("Prime123");
        registerPage.clickOnRegisterButton();
        Assert.assertEquals("Your registration completed", registerPage.getYourRegCompletedText(),
                "Registration not successful");
        registerPage.clickOnContinueButton();
    }


}

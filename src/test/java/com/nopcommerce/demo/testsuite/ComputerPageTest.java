package com.nopcommerce.demo.testsuite;

import com.nopcommerce.demo.pages.BuildYourOwnComputerPage;
import com.nopcommerce.demo.pages.ComputerPage;
import com.nopcommerce.demo.pages.DesktopsPage;
import com.nopcommerce.demo.pages.HomePage;
import com.nopcommerce.demo.testbase.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import resources.testdata.TestData;

/**
 * Created by Jay Vaghani
 */
public class ComputerPageTest extends TestBase {

    HomePage homePage;
    ComputerPage computerPage;
    DesktopsPage desktopsPage;
    BuildYourOwnComputerPage buildYourOwnComputerPage;

    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        homePage = new HomePage();
        computerPage = new ComputerPage();
        desktopsPage = new DesktopsPage();
        buildYourOwnComputerPage = new BuildYourOwnComputerPage();
    }

    @Test(priority = 1, groups = {"smoke", "regression"})
    public void verifyUserShouldNavigateToComputerPageSuccessfully() {
        homePage.clickOnMenuTab("Computers");
        String expectedMessage = "Computers";
        String actualMessage = computerPage.getPageTitleText();
        Assert.assertEquals(expectedMessage, actualMessage, "Computers page not displayed");
    }

    @Test(priority = 2, groups = {"sanity", "regression"})
    public void verifyUserShouldNavigateToDesktopsPageSuccessfully() {
        homePage.clickOnMenuTab("Computers");
        computerPage.clickOnSubMenu("Desktops");
        String expectedMessage = "Desktops";
        String actualMessage = desktopsPage.getPageTitleText();
        Assert.assertEquals(expectedMessage, actualMessage, "Desktops page not displayed");
    }

    @Test(dataProvider = "buildYourComputerData", dataProviderClass = TestData.class, priority = 3, groups = {"regression"})
    public void verifyThatUserShouldBuildYourOwnComputerAndAddToCartSuccessfully(String processor, String ram,
                                                                                 String hdd, String os, String software) {
        homePage.clickOnMenuTab("Computers");
        computerPage.clickOnSubMenu("Desktops");
        desktopsPage.selectProduct("Build your own computer");
        buildYourOwnComputerPage.selectProcessor(processor);
        buildYourOwnComputerPage.selectRam(ram);
        buildYourOwnComputerPage.selectHDD(hdd);
        buildYourOwnComputerPage.selectOS(os);
        buildYourOwnComputerPage.selectSoftware(software);
        buildYourOwnComputerPage.clickOnAddToCartButton();
        String expectedMessage = "The product has been added to your shopping cart";
        String actualMessage = buildYourOwnComputerPage.getProductAddedMessage();
        Assert.assertEquals(expectedMessage, actualMessage, "Product does not added to cart");
    }
}

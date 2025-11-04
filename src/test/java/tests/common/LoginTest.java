package tests.common;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.common.BottomNavigationBase;
import pages.common.LoginPageBase;

public class LoginTest extends BaseTest {

    @Test(description = "Log in")
    public void logInNegative() {
        BottomNavigationBase bottomNavigation = getPageManager().getPage(BottomNavigationBase.class);
        LoginPageBase loginPage = getPageManager().getPage(LoginPageBase.class);

        bottomNavigation.goToLogin();

        Assert.assertTrue(loginPage.isLoginScreenDisplayed());
        loginPage.enterEmail("poo");
        loginPage.enterPassword("");
        loginPage.clickLoginButton();
        Assert.assertEquals(loginPage.getLoginInputErrorMessageText(), "Please enter a valid email address");
    }
}

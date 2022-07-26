import Provider.InvalidUserNameProvider;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class VerifyLogin extends BaseTest {

    @Test
    public void VerifyLoginWithValidCredentials() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.openPage();
        loginPage.enterUserName("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickOnLoginbtn();
        Assert.assertEquals(loginPage.isProductPageDisplayed(),true, "login failed");
    }

    @Test(dataProvider = "InvalidUserNameProvider", dataProviderClass = InvalidUserNameProvider.class)
    public void VerifyLoginWithInvalidUsername(String username) {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.openPage();
        loginPage.enterUserName(username);
        loginPage.enterPassword("secret_sauce");
        loginPage.clickOnLoginbtn();
        String actualErrorMessage = loginPage.getErrorMessage().getText();
        Assert.assertEquals(actualErrorMessage, "Epic sadface: Username and password do not match any user in this service", "Login failed");

    }

    @Test
    public void VerifyLoginWithBlankUsernameField() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.openPage();
        loginPage.enterUserName("");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickOnLoginbtn();
        String actualErrorMessage = loginPage.getErrorMessage().getText();
        Assert.assertEquals(actualErrorMessage, "Epic sadface: Username is required", "Login failed");

    }

    @Test
    public void VerifyLoginWithInvalidPassword(){
        LoginPage loginPage = new LoginPage(driver);

        loginPage.openPage();
        loginPage.enterUserName("standard_user");
        loginPage.enterPassword("invalid_password");
        loginPage.clickOnLoginbtn();
        String actualErrorMessage = loginPage.getErrorMessage().getText();
        Assert.assertEquals(actualErrorMessage, "Epic sadface: Username and password do not match any user in this service", "Login failed");
    }

    @Test
    public void VerifyLoginWithBlankPasswordField(){
        LoginPage loginPage = new LoginPage(driver);

        loginPage.openPage();
        loginPage.enterUserName("standard_user");
        loginPage.enterPassword("");
        loginPage.clickOnLoginbtn();
        String actualErrorMessage = loginPage.getErrorMessage().getText();
        Assert.assertEquals(actualErrorMessage, "Epic sadface: Password is required", "Login failed");
    }

    @Test
    public void VerifyLoginWithAllFieldsBlank(){
        LoginPage loginPage = new LoginPage(driver);

        loginPage.openPage();
        loginPage.enterUserName("");
        loginPage.enterPassword("");
        loginPage.clickOnLoginbtn();
        String actualErrorMessage = loginPage.getErrorMessage().getText();
        Assert.assertEquals(actualErrorMessage, "Epic sadface: Username is required", "Login failed");
    }
}

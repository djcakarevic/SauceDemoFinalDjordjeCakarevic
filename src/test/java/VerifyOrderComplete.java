import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class VerifyOrderComplete extends BaseTest {
    @Test
    public void verifyAddProductToCartByName() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.openPage();
        loginPage.enterUserName("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickOnLoginbtn();

        ProductPage productPage = new ProductPage(driver);
        Thread.sleep(1000);
        Assert.assertEquals(productPage.isProductPageDisplayed(),true,"Login failed");
        Assert.assertEquals(productPage.isCartEmpty(),true,"Cart is not empty");


        productPage.addProductToCartByName("Sauce Labs Backpack");
        productPage.addProductToCartByName("Sauce Labs Onesie");
        productPage.clickOnCartBtn();
        Thread.sleep(1000);

        CartPage cartPage = new CartPage(driver);
        Assert.assertEquals(cartPage.isCartPageDisplayed(),true,"CartPage is not displayed");
        cartPage.clickOnCheckoutBtn();
        Assert.assertEquals(cartPage.isCheckoutPageDisplayed(),true, "checkout page is not displayed");

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterFirstName("Djordje");
        checkoutPage.enterLastName("Cakarevic");
        checkoutPage.enterZipCode("11000");
        checkoutPage.clickOnContinueBtn();

        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);
        Assert.assertEquals(checkoutOverviewPage.isCheckoutOverviewPageDisplayed(),true,"checkout overview message is not diplayed");
        checkoutOverviewPage.clickOnFinishBtn();
        Assert.assertEquals(checkoutOverviewPage.isCheckoutCompletePageDisplayed(), true, "Checkout complete page is not displayed");
    }
}

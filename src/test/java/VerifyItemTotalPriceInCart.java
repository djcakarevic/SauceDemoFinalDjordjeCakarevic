import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

import java.util.List;

public class VerifyItemTotalPriceInCart extends BaseTest {
    @Test
    public void verifyAddProductToCartByName() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.openPage();
        loginPage.enterUserName("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickOnLoginbtn();

        ProductPage productPage = new ProductPage(driver);
        Thread.sleep(1000);
        Assert.assertEquals(productPage.isProductPageDisplayed(), true, "Login failed");
        Assert.assertEquals(productPage.isCartEmpty(), true, "Cart is not empty");


        productPage.addProductToCartByName("Sauce Labs Backpack");
        productPage.addProductToCartByName("Sauce Labs Onesie");
        productPage.clickOnCartBtn();
        Thread.sleep(1000);

        CartPage cartPage = new CartPage(driver);
        Assert.assertEquals(cartPage.isCartPageDisplayed(), true, "CartPage is not displayed");
        cartPage.clickOnCheckoutBtn();
        Assert.assertEquals(cartPage.isCheckoutPageDisplayed(), true, "checkout page is not displayed");

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterFirstName("Djordje");
        checkoutPage.enterLastName("Cakarevic");
        checkoutPage.enterZipCode("11000");
        checkoutPage.clickOnContinueBtn();

        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);
        checkoutOverviewPage.isPriceEquals();
        System.out.println("Item total price without tax is:$" + checkoutOverviewPage.getItemTotalPrice());

    }
}

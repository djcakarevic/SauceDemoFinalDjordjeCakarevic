import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductPage;

public class VerifyRemoveFromCart extends BaseTest{
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
        cartPage.removeProductFromCartByName("Sauce Labs Onesie");
        cartPage.removeProductFromCartByName("Sauce Labs Backpack");
        Assert.assertEquals(productPage.isCartEmpty(),true,"Cart is not empty");

    }
}

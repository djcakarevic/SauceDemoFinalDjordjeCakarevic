import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductPage;

public class VerifyAddCheapestProductToCart extends BaseTest {

    @Test
    public void VerifyAddCheapestProductToCartWithoutSorting() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.openPage();
        loginPage.enterUserName("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickOnLoginbtn();

        ProductPage productPage = new ProductPage(driver);
        Thread.sleep(1000);
        Assert.assertEquals(productPage.isProductPageDisplayed(),true,"Login failed");
        Assert.assertEquals(productPage.isCartEmpty(),true,"Cart is not empty");

        productPage.addCheapestProductToCart();
        productPage.clickOnCartBtn();

        CartPage cartPage = new CartPage(driver);
        cartPage.isCartPageDisplayed();
        cartPage.verifyItemInCartByName("Sauce Labs Onesie");
        cartPage.removeProductFromCartByName("Sauce Labs Onesie");
    }

    @Test
    public void VerifyAddCheapestProductToCartWithSorting() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.openPage();
        loginPage.enterUserName("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickOnLoginbtn();

        ProductPage productPage = new ProductPage(driver);
        Thread.sleep(1000);
        Assert.assertEquals(productPage.isProductPageDisplayed(),true,"Login failed");
        Assert.assertEquals(productPage.isCartEmpty(),true,"Cart is not empty");

        productPage.selectLoToHi();
        Assert.assertEquals(productPage.isListSortedFromLoToHiByPrice(), true, "List is not sorted by Low to High");
        productPage.getFirstProduct();
        productPage.clickOnCartBtn();


        CartPage cartPage = new CartPage(driver);
        cartPage.isCartPageDisplayed();
        cartPage.verifyItemInCartByName("Sauce Labs Onesie");
        cartPage.removeProductFromCartByName("Sauce Labs Onesie");
    }
}

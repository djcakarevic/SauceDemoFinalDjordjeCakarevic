package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class CartPage extends BasePage {
    private String url;

    public CartPage(WebDriver driver) {
        super(driver);
        this.url = super.url + "cart.html";
    }

    public void openPage() {
        driver.get(this.url);
        driver.manage().window().maximize();
    }

    private WebElement getRemoveFromCart() {
        return driver.findElement(By.xpath("//button[text()= 'Remove']"));
    }

    public void clickOnRemove() {
        this.getRemoveFromCart().click();
    }

    public void removeProductFromCartByName(String name) {
        WebElement listCartContentContainer = driver.findElement(By.id("cart_contents_container"));
        List<WebElement> cartItem = listCartContentContainer.findElements(By.className("cart_item"));
        Integer indexElement = 0;
        for (int i = 0; i < cartItem.size(); i++) {
            WebElement inventoryItemName = cartItem.get(i).findElement(By.className("inventory_item_name"));
            if (inventoryItemName.getText().equals(name)) {
                indexElement = i;
                break;
            }
        }
        WebElement RemoveBtn = cartItem.get(indexElement).findElement(By.xpath(".//button"));
        RemoveBtn.click();
    }

    public Boolean isCartPageDisplayed() {
        Boolean toReturn = true;
        List<WebElement> listInventoryContainer = driver.findElements(By.id("cart_contents_container"));
        if (listInventoryContainer.size() == 1) {
            toReturn = true;
        } else {
            toReturn = false;
        }
        return toReturn;
    }

    public void isCartEmpty() {
        Integer numOfProductsBeforeAdd;

        WebElement shoppingCartLink = driver.findElement(By.className("shopping_cart_link"));
        List<WebElement> spanShoppingCartBadge = shoppingCartLink.findElements(By.xpath(".//span"));

        if (spanShoppingCartBadge.size() == 0) {
            numOfProductsBeforeAdd = 0;
        } else {
            numOfProductsBeforeAdd = Integer.parseInt(spanShoppingCartBadge.get(0).getText());
        }

        Assert.assertEquals(Integer.parseInt(spanShoppingCartBadge.get(0).getText()), numOfProductsBeforeAdd + 1, "Number is not equal");

    }

    private WebElement getCheckoutBtn() {
        return driver.findElement(By.id("checkout"));
    }

    public void clickOnCheckoutBtn() {
        this.getCheckoutBtn().click();
    }

    public Boolean isCheckoutPageDisplayed() {
        Boolean toReturn = true;
        List<WebElement> checkoutInfoContainer = driver.findElements(By.id("checkout_info_container"));
        if (checkoutInfoContainer.size() == 1) {
            toReturn = true;
        } else {
            toReturn = false;
        }
        return toReturn;
    }

    public WebElement getFirstProductName() {
        return driver.findElement(By.xpath("//div[@class='inventory_item_name']"));
    }

    public boolean verifyItemInCartByName(String itemName) {
        boolean returnValue = false;

        List<WebElement> cartItems = driver.findElements(By.className("cart_item"));

        for (int i = 0; i < cartItems.size(); i++){
            String cartItem = cartItems.get(i).findElement(By.xpath(".//div[@class='inventory_item_name']")).getText();
            if (itemName.equals(cartItem)){
                returnValue = true;
                break;
            }
        }

        return returnValue;
    }
}



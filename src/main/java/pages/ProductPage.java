package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductPage extends BasePage {
    private String url;

    public ProductPage(WebDriver driver) {
        super(driver);
        this.url = super.url + "inventory.html";
    }

    private WebElement getProductByName(String name) {
        //return driver.findElement(By.xpath("//div[text()= '" + name + "']"));
        return driver.findElement(By.linkText(name));
    }

    public void clickOnProductByName(String name) {
        this.getProductByName(name).click();
    }

    private WebElement getAddToCartBtn() {
        return driver.findElement(By.xpath("//button[text()= 'Add to cart']"));
    }

    public void clickOnAddToCartBtn() {
        this.getAddToCartBtn().click();
    }

    private WebElement getCartBtn() {
        return driver.findElement(By.className("shopping_cart_link"));
    }

    public void clickOnCartBtn() {
        this.getCartBtn().click();
    }

    public Boolean isProductPageDisplayed() {
        Boolean toReturn = true;
        List<WebElement> listInventoryContainer = driver.findElements(By.id("inventory_container"));
        if (listInventoryContainer.size() == 2) {
            toReturn = true;
        } else {
            toReturn = false;
        }
        return toReturn;
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


    public void addProductToCartByName(String name) {
        WebElement listInventoryContainer = driver.findElement(By.id("inventory_container"));
        List<WebElement> listInventoryItem = listInventoryContainer.findElements(By.className("inventory_item"));
        Integer indexElement = -1;
        for (int i = 0; i < listInventoryItem.size(); i++) {
            WebElement inventoryName = listInventoryItem.get(i).findElement(By.className("inventory_item_name"));
            if (inventoryName.getText().equals(name)) {
                indexElement = i;
                break;
            }
        }
        WebElement btnAdd = listInventoryItem.get(indexElement).findElement(By.xpath(".//button"));
        btnAdd.click();
    }

    public Boolean isCartEmpty() {
        Boolean toReturn = true;
        //Integer numOfProductsBeforeAdd;
        WebElement shoppingCartLink = driver.findElement(By.className("shopping_cart_link"));
        List<WebElement> spanShoppingCartBadge = shoppingCartLink.findElements(By.xpath(".//span"));

        if (spanShoppingCartBadge.size() == 0) {
            toReturn = true;
        } else {
            toReturn = false;
        }
        return toReturn;

    }

    public WebElement getCheapestItem() {
        List<WebElement> listInventoryItems = driver.findElements(By.className("inventory_item"));

        double lowestPrice = Double.parseDouble(listInventoryItems.get(0).findElement(
                By.xpath(".//div[@class='inventory_item_price']")).getText().substring(1));

        WebElement lowestPriceItem = null;

        for (int i = 0; i < listInventoryItems.size(); i++) {
            Double price = Double.parseDouble(listInventoryItems.get(i).findElement(
                    By.xpath(".//div[@class='inventory_item_price']")).getText().substring(1));
            if (price < lowestPrice) {
                lowestPrice = price;
                lowestPriceItem = listInventoryItems.get(i);
            }
        }
        return lowestPriceItem;

    }

    public String addCheapestItem(WebElement item) {
        item.findElement(By.xpath(".//button")).click();
        return item.findElement(By.xpath(".//div[@class='inventory_item_name']")).getText();
    }

    private WebElement cheapestProduct() {
        return this.getCheapestItem();
    }

    public String addCheapestProductToCart() {
        return this.addCheapestItem(cheapestProduct());
    }

    private WebElement getSortContainer() {
        return driver.findElement(By.xpath(".//select[@class='product_sort_container']"));
    }

    private WebElement getLoToHiProductFromList() {
        return driver.findElement(By.xpath("//option[@value='lohi']"));
    }

    public void selectLoToHi() {
        this.getSortContainer().click();
        this.getLoToHiProductFromList().click();
    }

    public Boolean isListSortedFromLoToHiByPrice() {
        Boolean toReturn = true;

        WebElement inventoryList = driver.findElement(By.xpath("//div[@class='inventory_list']"));
        List<WebElement> inventoryItems = inventoryList.findElements(By.xpath(".//div[@class='inventory_item']"));

        Double currentMaxPrice = -1.0;

        for (int i = 0; i < inventoryItems.size(); i++) {
            WebElement inventoryItemPrice = inventoryItems.get(i).findElement(By.xpath(".//div[@class='inventory_item_price']"));
            String inventoryItemPriceValue = inventoryItemPrice.getText();
            String tempPrice = inventoryItemPriceValue.substring(1, inventoryItemPriceValue.length());

            Double dblPrice = Double.parseDouble(tempPrice);

            if (dblPrice >= currentMaxPrice) {
                currentMaxPrice = dblPrice;
            } else {
                toReturn = false;
                break;
            }
        }
        return toReturn;
    }


    public void getFirstProduct() {
        List<WebElement> listInventoryContainer = driver.findElements(By.xpath("//div[@class='inventory_item']"));
        WebElement btnAddProduct = listInventoryContainer.get(0).findElement(By.xpath(".//button"));
        btnAddProduct.click();
    }

}

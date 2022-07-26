package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class CheckoutOverviewPage extends BasePage{
    private String url;

    public CheckoutOverviewPage (WebDriver driver) {
        super(driver);
        this.url = super.url + "checkout-step-two.html";
    }

    public Boolean isCheckoutOverviewPageDisplayed() {
        Boolean toReturn = true;
        List<WebElement> checkoutSummaryContainer = driver.findElements(By.id("checkout_summary_container"));
        if (checkoutSummaryContainer.size() == 1) {
            toReturn = true;
        } else {
            toReturn = false;
        }
        return toReturn;
    }

    private WebElement getFinishBtn(){
        return driver.findElement(By.id("finish"));
    }
    public void clickOnFinishBtn(){
        this.getFinishBtn().click();
    }

    public Boolean isCheckoutCompletePageDisplayed() {
        Boolean toReturn = true;
        List<WebElement> checkoutCompleteContainer = driver.findElements(By.id("checkout_complete_container"));
        if (checkoutCompleteContainer.size() == 1) {
            toReturn = true;
        } else {
            toReturn = false;
        }
        return toReturn;
    }

    private WebElement getElementItemTotalPrice() {
        return driver.findElement(By.className("summary_subtotal_label"));
    }

    public Double getItemTotalPrice(){
        return Double.parseDouble(this.getElementItemTotalPrice().getText().substring(13));
    }
        public Boolean isPriceEquals () {
        Boolean toReturn = true;

        List<WebElement> cartList = driver.findElements(By.className("cart_item"));

        double firstItemPrice = Double.parseDouble(cartList.get(0).findElement(By.className("inventory_item_price")).getText().substring(1));
        double secondItemPrice = Double.parseDouble(cartList.get(1).findElement(By.className("inventory_item_price")).getText().substring(1));
        double totalPriceNoTax = firstItemPrice + secondItemPrice;

        WebElement summaryInfo = driver.findElement(By.className("summary_subtotal_label"));
        double summaryItemTotal = Double.parseDouble(summaryInfo.getText().substring(13));
        if (totalPriceNoTax == summaryItemTotal) {
            toReturn = true;
        } else {
            toReturn = false;
        }
        return toReturn;
    }

    }

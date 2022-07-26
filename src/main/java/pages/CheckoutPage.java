package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckoutPage extends BasePage{
        private String url;

        public CheckoutPage(WebDriver driver) {
            super(driver);
            this.url = super.url + "checkout-step-one.html";
        }

        private WebElement getFirstName(){
            return driver.findElement(By.id("first-name"));
        }
        public void enterFirstName(String firstName){
            this.getFirstName().sendKeys(firstName);
        }

        private WebElement getLastName(){
            return driver.findElement(By.id("last-name"));
        }
        public void enterLastName(String lastName) {
            this.getLastName().sendKeys(lastName);
        }

        private WebElement getZipCode(){
            return driver.findElement(By.id("postal-code"));
        }
        public void enterZipCode(String zipCode){
            this.getZipCode().sendKeys(zipCode);
        }

        private WebElement getContinueBtn(){
            return driver.findElement(By.id("continue"));
        }
        public void clickOnContinueBtn(){
            this.getContinueBtn().click();
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

}

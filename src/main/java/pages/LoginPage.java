package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class LoginPage extends BasePage{


    private WebElement enterUserName;
    private WebElement enterPassword;
    private WebElement btnLogin;
    private String url;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.url = super.url;

    }


    private WebElement getEnterUserName() {
        return driver.findElement(By.id("user-name"));
    }

    public void enterUserName(String username) {
        this.getEnterUserName().sendKeys(username);
    }

    private WebElement getEnterPassword() {
        return driver.findElement(By.id("password"));
    }

    public void enterPassword(String password) {
        this.getEnterPassword().sendKeys(password);
    }

    private WebElement getBtnLogin() {
        return driver.findElement(By.id("login-button"));
    }


    public void clickOnLoginbtn(){
        WebElement btnLogin = this.getBtnLogin();
        btnLogin.click();
    }

    public void waitUntilBtnLoginIsClickable(){
        WebDriverWait wait = new WebDriverWait(driver,60);
        wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void openPage(){
        driver.get(url);
        driver.manage().window().maximize();
    }

    public WebElement getErrorMessage() {
        return driver.findElement(By.xpath("//h3[@data-test='error']"));
    }

    public Boolean isProductPageDisplayed(){
        Boolean toReturn = true;
        List<WebElement> listInventoryContainer = driver.findElements(By.id("inventory_container"));
        if(listInventoryContainer.size() == 2) {
            toReturn = true;
        }
        else {
            toReturn = false;
        }
        return toReturn;
    }
}

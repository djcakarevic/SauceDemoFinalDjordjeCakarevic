import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    protected WebDriver driver;
    protected String url;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Slx Pc\\Downloads\\chromedriver_win32\\chromedriver.exe");
        this.driver = new ChromeDriver();
    }

    @AfterClass
    public void close() {

        this.driver.quit();
    }
}

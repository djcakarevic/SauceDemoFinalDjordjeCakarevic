package Provider;

import org.testng.annotations.DataProvider;

public class InvalidUserNameProvider {
    @DataProvider(name = "InvalidUserNameProvider")
    public static Object[][] getInvalidUserNameDataProvider(){
        return new Object[][] {
                { "SecretSauce"},
                { "SaucySauce" },
                { "SauceUser" }
        };
    }
}

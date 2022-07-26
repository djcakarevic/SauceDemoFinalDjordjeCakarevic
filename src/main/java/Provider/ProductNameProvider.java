package Provider;

import org.testng.annotations.DataProvider;

public class ProductNameProvider {
    @DataProvider(name = "ProductNameProvider")
    public static Object[][] getProductNameProvider(){
        return new Object[][] {
                { "Sauce Labs Onesie"},
                { "Sauce Labs Backpack" }

        };
    }
}

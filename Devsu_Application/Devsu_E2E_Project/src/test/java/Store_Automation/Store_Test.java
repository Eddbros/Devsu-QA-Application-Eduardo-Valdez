package Store_Automation;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;
import org.testng.Assert;

public class Store_Test {
    WebDriver driver;
    Store_Page stPage;

    boolean cellphonesInCart = false;

    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        stPage = new Store_Page(driver);
        driver.manage().window().maximize();
        driver.get("https://demoblaze.com");

    }
    @Test (priority = 1)
    public void cart_test (){
        if(!cellphonesInCart){
            stPage.addToCart(driver);
            cellphonesInCart = true;
        }
        Assert.assertEquals(cellphonesInCart, true);
    }

    @Test (priority = 2)
    public void purchase_Test(){
        if(cellphonesInCart){
            Assert.assertEquals(stPage.purchase(driver), "Thank you for your purchase!" , "The order was not placed correctly");
        }

    }
    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}

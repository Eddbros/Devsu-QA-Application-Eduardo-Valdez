package Store_Automation;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Store_Page {
    private WebDriver driver;
    private WebDriverWait wait;

    By homePageLocator = By.id("nava");

    By cartLocator = By.id("cartur");
    By cellPhone1locator = By.xpath("//*[@id='tbodyid']/div[1]/div/div/h4/a");
    By cellPhone2locator = By.cssSelector("#tbodyid > div:nth-child(2) > div > div > h4 > a");
    By addToCartBtnLocator = By.cssSelector("#tbodyid > div.row > div > a");
    By placeOrderBtnLocator = By.xpath("//*[@id='page-wrapper']/div/div[2]/button");

    By orderModalLocator = By.id("orderModalLabel");
    By nameLocator = By.id("name");
    By countryLocator = By.id("country");
    By cityLocator = By.id("city");
    By creditCardLocator = By.id("card");
    By monthLocator = By.id("month");
    By yearLocator = By.id("year");
    By purchaseBtnLocator = By.cssSelector("#orderModal > div > div > div.modal-footer > button.btn.btn-primary");

    By resultLocator = By.xpath("/html/body/div[10]/h2");

    List<By> phoneList = new ArrayList<>();

    public Store_Page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void addToCart (WebDriver driver){
        phoneList.add(cellPhone1locator);
        phoneList.add(cellPhone2locator);

        for(By phone : phoneList){
            wait.until(ExpectedConditions.elementToBeClickable(phone));
            WebElement phoneElement = driver.findElement(phone);
            phoneElement.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartBtnLocator));
            driver.findElement(addToCartBtnLocator).click();
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            alert.accept();
            wait.until(ExpectedConditions.elementToBeClickable(homePageLocator)).click();
        }
    }

    public String purchase(WebDriver driver) {
        wait.until(ExpectedConditions.presenceOfElementLocated(cartLocator));
        driver.findElement(cartLocator).click();
        driver.findElement(placeOrderBtnLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(orderModalLocator));
        driver.findElement(nameLocator).sendKeys("Eduardo");
        driver.findElement(countryLocator).sendKeys("Mexico");
        driver.findElement(cityLocator).sendKeys("Motozintla");
        driver.findElement(creditCardLocator).sendKeys("123456789");
        driver.findElement(monthLocator).sendKeys("12");
        driver.findElement(yearLocator).sendKeys("2030");
        driver.findElement(purchaseBtnLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(resultLocator));
        String order = driver.findElement(resultLocator).getText();
        return order;

    }
}

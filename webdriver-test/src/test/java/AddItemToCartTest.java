import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddItemToCartTest {

    private static final String LAPTOP_URL = "https://shop.asus.com/us/90nr0ay2-m004p0-ga503rs.html";
    private static final String EXPECTED_PRODUCT_ITEM_NAME = "GA503RS";
    private WebDriver webDriver;
    private WebDriverWait webDriverWait;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        webDriver.manage().window().maximize();
        webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
        webDriver.get(LAPTOP_URL);
    }

    @Test
    public void addProductToCartTest() {
        WebElement buttonAddToCart = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//button[@class='action primary tocart']")
        ));
        buttonAddToCart.click();
        WebElement nameOfTheDevice = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//span[@class='bundle-title-lines']")
        ));
        Assert.assertEquals(nameOfTheDevice.getText(), EXPECTED_PRODUCT_ITEM_NAME);
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        webDriver.quit();
        webDriver = null;
    }

}

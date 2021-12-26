import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class ProductsTest {
    private WebDriver driver;

    @Before
    public  void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        //       driver.manage().window().maximize();
        driver.get("http://testfasttrackit.info/selenium-test/");
    }

    @Test
    public void productDisplayType(){
        Actions builder = new Actions(driver);
        builder.moveToElement(driver.findElement(By.cssSelector(".nav-1"))).build().perform();
        driver.findElement(By.cssSelector(".nav-1-1 a")).click();
        driver.findElement(By.cssSelector(".category-products>div.toolbar .list")).click();
        WebElement learnMoreButton = driver.findElement(By.cssSelector(".odd .desc.std a"));
        String expected = driver.findElement(By.cssSelector(".odd .desc.std a")).getText();
        String actual = "Learn More";
        Assert.assertEquals(expected,actual);
        Assert.assertTrue(learnMoreButton.isDisplayed());
    }

    @After
    public void closeBrowser(){
        driver.quit();
    }
}

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;


public class RegisterTest {
    private  WebDriver driver;


    @Before
    public  void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
//      driver.manage().window().maximize();
        driver.get("http://testfasttrackit.info/selenium-test/");
    }

    @Test
    public void registerCompletion(){
        driver.findElement(By.cssSelector(".skip-account .label")).click();
        driver.findElement(By.cssSelector(".skip-active li:nth-child(5)")).click();
        driver.findElement(By.id("firstname")).sendKeys("dasdasdasfdsgh");
        driver.findElement(By.id("middlename")).sendKeys("dasfuofdierhfdho");
        driver.findElement(By.id("lastname")).sendKeys("dasdasfuofdierhfdho");
        driver.findElement(By.name("email")).sendKeys("testfjfdjdsjf"+ randomAlphanumeric(4) +"@gmail.com");
        driver.findElement(By.id("password")).sendKeys("Test123");
        driver.findElement(By.id("confirmation")).sendKeys("Test123");
        driver.findElement(By.name("is_subscribed")).click();
        driver.findElement(By.cssSelector(".buttons-set button.button")).click();

        String actualSuccesText = driver.findElement(By.cssSelector("li span")).getText();
        String expectedSuccesText = "Thank you for registering with Madison Island.";
        Assert.assertEquals(expectedSuccesText,actualSuccesText);

    }

    @After
    public void closeBrowser(){
        driver.quit();
    }
}
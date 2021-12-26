import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AccountProfileTest {
        private  WebDriver driver;

    @Before
    public  void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
//        driver.manage().window().maximize();
        driver.get("http://testfasttrackit.info/selenium-test/");
    }

    @Test
    public void changeAccPassword(){
        WebElement accountLink = driver.findElement(By.cssSelector(".skip-account .label"));
        accountLink.click();
        driver.findElement(By.cssSelector(".skip-active .first a")).click();
        driver.findElement(By.id("email")).sendKeys("gabi.mihalache16@yahoo.com");
        driver.findElement(By.id("pass")).sendKeys("test123");
        driver.findElement(By.id("send2")).click();
        driver.findElement(By.cssSelector(".box-content p a")).click();
        driver.findElement(By.id("current_password")).sendKeys("test123");
        driver.findElement(By.id("password")).sendKeys("test123");
        driver.findElement(By.id("confirmation")).sendKeys("test123");
        driver.findElement(By.cssSelector(".buttons-set button")).click();

        String actualSuccessText = driver.findElement(By.cssSelector(".success-msg span")).getText();
        String expectedSuccessText = "The account information has been saved.";
        Assert.assertEquals(expectedSuccessText,actualSuccessText);
    }

    @Test
    public void changeAccountInformation(){
        WebElement accountLink = driver.findElement(By.cssSelector(".skip-account .label"));
        accountLink.click();
        driver.findElement(By.cssSelector(".skip-active .first a")).click();
        driver.findElement(By.id("email")).sendKeys("gabi.mihalache16@yahoo.com");
        driver.findElement(By.id("pass")).sendKeys("test123");
        driver.findElement(By.id("send2")).click();
        driver.findElement(By.cssSelector(".box-title [href*=\"account/edit/\"]")).click();
        driver.findElement(By.id("firstname")).sendKeys("A");
        driver.findElement(By.id("middlename")).sendKeys("A");
        driver.findElement(By.id("lastname")).sendKeys("A");
        driver.findElement(By.id("current_password")).sendKeys("test123");
        driver.findElement(By.cssSelector(".buttons-set button")).click();

        String actualSuccessText = driver.findElement(By.cssSelector(".success-msg span")).getText();
        String expectedSuccessText = "The account information has been saved.";
        Assert.assertEquals(expectedSuccessText,actualSuccessText);

        //change back the info
        driver.findElement(By.cssSelector(".box-title [href*=\"account/edit/\"]")).click();
        driver.findElement(By.id("firstname")).sendKeys(Keys.CONTROL + "a");
        driver.findElement(By.id("firstname")).sendKeys(Keys.DELETE.toString());
        driver.findElement(By.id("firstname")).sendKeys("dasdasd");

        driver.findElement(By.id("middlename")).sendKeys(Keys.CONTROL + "a");
        driver.findElement(By.id("middlename")).sendKeys(Keys.DELETE.toString());
        driver.findElement(By.id("middlename")).sendKeys("asdasdad");

        driver.findElement(By.id("lastname")).sendKeys(Keys.CONTROL + "a");
        driver.findElement(By.id("lastname")).sendKeys(Keys.DELETE.toString());
        driver.findElement(By.id("lastname")).sendKeys("asdasda");
        driver.findElement(By.id("current_password")).sendKeys("test123");
        driver.findElement(By.cssSelector(".buttons-set button")).click();

        Assert.assertEquals(expectedSuccessText,actualSuccessText);
    }

    @After
    public void closeBrowser(){
        driver.quit();
    }
}

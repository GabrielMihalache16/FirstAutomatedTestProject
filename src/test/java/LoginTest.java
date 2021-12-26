import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {
    private  WebDriver driver;

    @Before
    public  void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
//        driver.manage().window().maximize();
        driver.get("http://testfasttrackit.info/selenium-test/");
    }

    @Test
    public void loginWithValidCredentials(){
        WebElement accountLink = driver.findElement(By.cssSelector(".skip-account .label"));
        accountLink.click();
        driver.findElement(By.cssSelector(".skip-active .first a")).click();
        driver.findElement(By.id("email")).sendKeys("gabi.mihalache16@yahoo.com");
        driver.findElement(By.id("pass")).sendKeys("test123");
        driver.findElement(By.id("send2")).click();

        String welcomeText = driver.findElement(By.cssSelector("p.hello")).getText();
        Assert.assertEquals("Hello, dasdasd asdasdad asdasda!", welcomeText);
    }

    @After
    public void closeBrowser(){
        driver.quit();
    }
}

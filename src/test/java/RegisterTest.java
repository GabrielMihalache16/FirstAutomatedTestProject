import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RegisterTest {
    public static void main(String[] args) {
        registerCompletion();
    }



    public static void registerCompletion(){
        System.setProperty("webdriver.chrome.driver","resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://testfasttrackit.info/selenium-test/");
        driver.findElement(By.cssSelector("#header > div > div.skip-links > div > a > span.label")).click();
        driver.findElement(By.cssSelector("#header-account > div > ul > li:nth-child(5) > a")).click();
        driver.findElement(By.id("firstname")).sendKeys("dasdasdasfdsgh");
        driver.findElement(By.id("middlename")).sendKeys("dasfuofdierhfdho");
        driver.findElement(By.id("lastname")).sendKeys("dasdasfuofdierhfdho");
        driver.findElement(By.name("email")).sendKeys("testfjfdjdsjf@gmail.com");
        driver.findElement(By.id("password")).sendKeys("Test123");
        driver.findElement(By.id("confirmation")).sendKeys("Test123");
        driver.findElement(By.name("is_subscribed")).click();


    }
}
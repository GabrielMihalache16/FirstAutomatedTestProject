import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WishListTest {
    private  WebDriver driver;

    @Before
    public  void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
//        driver.manage().window().maximize();
        driver.get("http://testfasttrackit.info/selenium-test/");
    }

    @Test
    public void addToWishList_NoAccount(){
        driver.findElement(By.cssSelector(".nav-5")).click();
        driver.findElement(By.cssSelector(".product-info .button")).click();
        driver.findElement(By.cssSelector(".add-to-links li:first-child a")).click();
        WebElement loginTitleElement = driver.findElement(By.cssSelector(".page-title h1"));
        String loginTitleGetText = loginTitleElement.getText();
        String loginTitleActual = "LOGIN OR CREATE AN ACCOUNT";
        Assert.assertTrue(loginTitleElement.isDisplayed());
        Assert.assertEquals(loginTitleGetText,loginTitleActual);
    }

    @Test
    public void addToWishList_YesAccount(){
        WebElement accountLink = driver.findElement(By.cssSelector(".skip-account .label"));
        accountLink.click();
        driver.findElement(By.cssSelector(".skip-active .first a")).click();
        driver.findElement(By.id("email")).sendKeys("gabi.mihalache16@yahoo.com");
        driver.findElement(By.id("pass")).sendKeys("test123");
        driver.findElement(By.id("send2")).click();
        driver.findElement(By.cssSelector(".logo")).click();
        driver.findElement(By.cssSelector(".products-grid [alt=\"Linen Blazer\"]")).click();
        driver.findElement(By.cssSelector("[alt=White]")).click();
        driver.findElement(By.id("swatch79")).click();
        driver.findElement(By.cssSelector(".link-wishlist")).click();

        String actualSuccessText = driver.findElement(By.cssSelector(".success-msg")).getText();
        String expectedSuccessText = "Linen Blazer has been added to your wishlist. Click here to continue shopping.";
        Assert.assertEquals(expectedSuccessText,actualSuccessText);
    }

    @Test
    public void addAllToCartFromWishList(){
        WebElement accountLink = driver.findElement(By.cssSelector(".skip-account .label"));
        accountLink.click();
        driver.findElement(By.cssSelector(".skip-active .first a")).click();
        driver.findElement(By.id("email")).sendKeys("gabi.mihalache16@yahoo.com");
        driver.findElement(By.id("pass")).sendKeys("test123");
        driver.findElement(By.id("send2")).click();
        driver.findElement(By.cssSelector(".logo")).click();
        driver.findElement(By.cssSelector(".products-grid [alt=\"Linen Blazer\"]")).click();
        driver.findElement(By.cssSelector("[alt=White]")).click();
        driver.findElement(By.id("swatch79")).click();
        driver.findElement(By.cssSelector(".link-wishlist")).click();

        //Adding everything to cart.
        driver.findElement(By.cssSelector(".button.btn-add")).click();
        String successMsg = driver.findElement(By.cssSelector(".success-msg")).getText();

        //Asserting a message that has dynamic numbers in it.
        Assert.assertTrue(successMsg.contains("product(s) have been added to shopping cart: \"Linen Blazer\"."));
    }

    @After
    public void closeBrowser(){
        driver.quit();
    }
}


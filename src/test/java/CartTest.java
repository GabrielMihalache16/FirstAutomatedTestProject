import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.Random;
import java.util.List;

public class CartTest {
    Random rand = new Random();

    private WebDriver driver;

    @Before
    public  void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
 //       driver.manage().window().maximize();
        driver.get("http://testfasttrackit.info/selenium-test/");
    }

    @Test
    public void addToCart(){
        driver.findElement(By.cssSelector(".products-grid [alt=\"Linen Blazer\"]")).click();
        driver.findElement(By.cssSelector("[alt=White]")).click();
        driver.findElement(By.id("swatch79")).click();
        driver.findElement(By.cssSelector(".add-to-cart-buttons button")).click();
        WebElement proceedToCheckOutButton = driver.findElement(By.cssSelector("div.cart-totals button"));
        String actualSuccesText = driver.findElement(By.cssSelector(".success-msg span")).getText();
        String expectedSuccesText = "Linen Blazer was added to your shopping cart.";
        Assert.assertEquals(expectedSuccesText,actualSuccesText);
        Assert.assertTrue(proceedToCheckOutButton.isDisplayed());
    }

    @Test
    public void deleteCartItemsOneByOne(){
        String actualSuccessText;
        String expectedSuccessText;
        driver.findElement(By.cssSelector(".products-grid [alt=\"Linen Blazer\"]")).click();
        driver.findElement(By.cssSelector("[alt=White]")).click();
        driver.findElement(By.id("swatch79")).click();
        driver.findElement(By.cssSelector(".add-to-cart-buttons button")).click();

        actualSuccessText = driver.findElement(By.cssSelector(".success-msg span")).getText();
        expectedSuccessText = "Linen Blazer was added to your shopping cart.";
        Assert.assertEquals(expectedSuccessText,actualSuccessText);

        driver.findElement(By.cssSelector(".logo")).click();
        driver.findElement(By.cssSelector(".products-grid [alt=\"Lafayette Convertible Dress\"]")).click();
        driver.findElement(By.cssSelector("[alt=Blue]")).click();
        driver.findElement(By.id("swatch76")).click();
        driver.findElement(By.cssSelector(".add-to-cart-buttons button")).click();

        actualSuccessText = driver.findElement(By.cssSelector(".success-msg span")).getText();
        expectedSuccessText = "Lafayette Convertible Dress was added to your shopping cart.";
        Assert.assertEquals(expectedSuccessText,actualSuccessText);
        WebElement linenItem = driver.findElement(By.cssSelector("td .product-name [href*=\"linen\"]"));
        Assert.assertTrue(linenItem.isDisplayed());
        WebElement lafayetteItem = driver.findElement(By.cssSelector("td .product-name [href*=\"lafa\"]"));
        Assert.assertTrue(lafayetteItem.isDisplayed());

        int removeButtonsLength = driver.findElements(By.cssSelector("td.product-cart-remove a")).size();
//  sau      int removeButtonsLength = Integer.parseInt(driver.findElement(By.cssSelector(".count")).getText());
        for (int i = 0; i<removeButtonsLength; i++){
            driver.findElement(By.cssSelector("tr.first.odd td.product-cart-remove a")).click();
        }
        actualSuccessText = driver.findElement(By.cssSelector("div h1")).getText();
        expectedSuccessText = "SHOPPING CART IS EMPTY";
        Assert.assertEquals(expectedSuccessText,actualSuccessText);
    }

    @Test
    public void editCartItemQuantity(){
        driver.findElement(By.cssSelector(".products-grid [alt=\"Linen Blazer\"]")).click();
        driver.findElement(By.cssSelector("[alt=White]")).click();
        driver.findElement(By.id("swatch79")).click();
        driver.findElement(By.cssSelector(".add-to-cart-buttons button")).click();
        driver.findElement(By.cssSelector(".cart-table .input-text.qty")).sendKeys(Keys.BACK_SPACE.toString());
        driver.findElement(By.cssSelector(".cart-table .input-text.qty")).sendKeys("3".toString());
        driver.findElement(By.cssSelector(".product-cart-actions button")).click();
        String actual = driver.findElement(By.cssSelector(".cart-table .input-text.qty")).getAttribute("value");
        String expected = "3";
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void addVipMembershipToCart(){
        driver.findElement(By.cssSelector(".level0.nav-6 a")).click();
        driver.findElement(By.cssSelector(".category-image a img")).click();
        Select month = new Select(driver.findElement(By.id("options_6_month")));
        Select day = new Select(driver.findElement(By.id("options_6_day")));
        Select year = new Select(driver.findElement(By.id("options_6_year")));

        //java.util.Random
        month.selectByValue(String.valueOf(rand.nextInt(13)));
        day.selectByValue(String.valueOf(rand.nextInt(32)));
        year.selectByValue("2021");
        driver.findElement(By.cssSelector(".add-to-cart-buttons button")).click();

        String actualSuccessText = driver.findElement(By.cssSelector(".success-msg span")).getText();
        String expectedSuccessText = "Madison Island VIP Membership - 1 Year was added to your shopping cart.";
        Assert.assertEquals(expectedSuccessText,actualSuccessText);
    }

    @Test
    public void addMembershipNotAllowedIfCartHasItems(){
        String actualSuccessText;
        String expectedSuccessText;
        driver.findElement(By.cssSelector(".products-grid [alt=\"Linen Blazer\"]")).click();
        driver.findElement(By.cssSelector("[alt=White]")).click();
        driver.findElement(By.id("swatch79")).click();
        driver.findElement(By.cssSelector(".add-to-cart-buttons button")).click();

        actualSuccessText = driver.findElement(By.cssSelector(".success-msg span")).getText();
        expectedSuccessText = "Linen Blazer was added to your shopping cart.";
        Assert.assertEquals(expectedSuccessText,actualSuccessText);

        driver.findElement(By.cssSelector(".logo")).click();
        driver.findElement(By.cssSelector(".level0.nav-6 a")).click();
        driver.findElement(By.cssSelector(".category-image a img")).click();
        Select month = new Select(driver.findElement(By.id("options_6_month")));
        Select day = new Select(driver.findElement(By.id("options_6_day")));
        Select year = new Select(driver.findElement(By.id("options_6_year")));
        month.selectByValue(String.valueOf(rand.nextInt(13)));
        day.selectByValue(String.valueOf(rand.nextInt(32)));
        year.selectByValue("2021");
        driver.findElement(By.cssSelector(".add-to-cart-buttons button")).click();

        //Red Error Message should be displayed now
        String actualErrorText = driver.findElement(By.cssSelector(".error-msg span")).getText();
        String expectedErrorText = "Nominal item can be purchased standalone only. To proceed please remove other items from the quote.";
        Assert.assertEquals(expectedSuccessText,actualSuccessText);
    }



    @After
    public void closeBrowser(){
        driver.quit();
    }

}

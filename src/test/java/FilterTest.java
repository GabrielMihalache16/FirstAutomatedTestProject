import org.checkerframework.checker.units.qual.A;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FilterTest {
    private WebDriver driver;

    @Before
    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
//        driver.manage().window().maximize();
        driver.get("http://testfasttrackit.info/selenium-test/");
    }

    @Test
    public void filterByColor() {
        driver.findElement(By.cssSelector("[alt=\"Shop Private Sales - Members Only\"]")).click();
        driver.findElement(By.cssSelector("[alt=White]")).click();

        String actual = driver.findElement(By.cssSelector("[alt=white]")).getAttribute("alt");
        String expected = "white";
        Assert.assertEquals(expected, actual);
    }


        @After
    public void closeBrowser(){
        driver.quit();
    }

}
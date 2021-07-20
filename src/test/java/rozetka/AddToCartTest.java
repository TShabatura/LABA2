package rozetka;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class AddToCartTest {
    private WebDriver driver;

    @BeforeTest
    public void profileSetUp(){
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
    }

    @BeforeMethod
    public void testSetUP(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rozetka.com.ua/ua/");
    }

    @Test
    public void addToCartTest(){
        driver.findElement(By.xpath("//main-page-sidebar//a[contains(@href, 'notebooks')]")).click();
//        WebDriverWait wait = new WebDriverWait(driver, 30);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[title='Ноутбуки']")));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector("a[title='Ноутбуки']")).click();
        driver.findElement(By.cssSelector("label[for='HP']")).click();
        driver.findElement(By.cssSelector("select.select-css")).click();
        driver.findElement(By.xpath("//option[contains(@value, 'expensive')]")).click();
        new WebDriverWait(driver, 30).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        driver.findElement(By.xpath("//button[@aria-label='Купити']")).click();
        driver.findElement(By.xpath("//a[@class='header__logo']")).click();
        driver.findElement(By.xpath("//main-page-sidebar//a[contains(@href, 'telefony')]")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        WebElement element = driver.findElement(By.cssSelector("a[title='Мобільні телефони']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
        driver.findElement(By.cssSelector("a[title='Мобільні телефони']")).click();
        driver.findElement(By.cssSelector("label[for='Apple']")).click();
        driver.findElement(By.cssSelector("select.select-css")).click();
        driver.findElement(By.xpath("//option[contains(@value, 'expensive')]")).click();
        new WebDriverWait(driver, 30).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        driver.findElement(By.xpath("//button[@aria-label='Купити']")).click();
        driver.findElement(By.xpath("//a[@class='header__logo']")).click();
        driver.findElement(By.xpath("//main-page-sidebar//a[contains(@href, 'game')]")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        element = driver.findElement(By.cssSelector("a[title='Ігрові приставки']"));
        actions.moveToElement(element);
        actions.perform();
        driver.findElement(By.cssSelector("a[title='Ігрові приставки']")).click();
        driver.findElement(By.cssSelector("label[for='Microsoft']")).click();
        driver.findElement(By.xpath("//button[@aria-label='Купити']")).click();
        driver.findElement(By.xpath("//button[@opencart]")).click();
        String total = driver.findElement(By.xpath("//div[@class='cart-receipt__sum-price']/span")).getText();
        Assert.assertTrue(Integer.valueOf(total) > 100000);
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}

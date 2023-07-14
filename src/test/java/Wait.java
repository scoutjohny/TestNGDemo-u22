import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Wait {
    WebDriver driver;

    @BeforeMethod
    @Parameters({"browser"})
    public void setup(String browser) {
        if (browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver_114.exe");
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver_0322.exe");
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        //Implicitno čekanje - Čeka dinamički svaki element da se pojavi određeni broj sekundi
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get("https://www.saucedemo.com/");
    }

    @AfterMethod
    @Parameters({"quit"})
    public void tearDown(String quit){
        if(quit.equalsIgnoreCase("yes")){
            driver.quit();
        }
    }

    @Test
    public void loginToSauceDemo() throws InterruptedException {

        //Grubo čekanje - zamrzava izvršavanje programa na određeni broj milisekundi
        //Thread.sleep(5000);
        driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");

        //Eksplicitno čekanje - Čeka dinamički da se određeni uslov ispuni za određeni broj sekundi i uslov proverava na svakih 0,5s
        WebDriverWait webDriverWait = new WebDriverWait(driver,Duration.ofSeconds(5));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#password")));
        driver.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#login-button")));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("#login-button"))));
        driver.findElement(By.cssSelector("#login-button")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");
    }

}

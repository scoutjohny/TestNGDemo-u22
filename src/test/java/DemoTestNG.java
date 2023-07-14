import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class DemoTestNG {
    WebDriver driver;
    @BeforeMethod
    @Parameters({"browser"})
    public void setup(String browser){
        if(browser.equals("chrome")){
            System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver_114.exe");
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")){
            System.setProperty("webdriver.gecko.driver","src/main/resources/geckodriver_0322.exe");
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
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
    @Parameters({"username","password","url","errorMessage","testType"})
    public void loginToSauceDemo(String username, String password, String url, @Optional String errorMessage, String testType ){
        if(!username.equals("empty")){
            driver.findElement(By.cssSelector("#user-name")).sendKeys(username);
        }
        if(!password.equals("empty")){
            driver.findElement(By.cssSelector("#password")).sendKeys(password);
        }
        driver.findElement(By.cssSelector("#login-button")).click();
        Assert.assertEquals(driver.getCurrentUrl(),url);
        if(testType.equals("negative")){
            Assert.assertEquals(driver.findElement(By.cssSelector("h3")).getText(),errorMessage);
        }
    }
//      POJEDINAČNI TESTOVI
//    @Test
//    public void loginToSauceDemo(){
//        driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
//        driver.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
//        driver.findElement(By.cssSelector("#login-button")).click();
//        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");
//    }
//
//    @Test
//    public void loginUsingWrongUsername(){
//        driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user1");
//        driver.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
//        driver.findElement(By.cssSelector("#login-button")).click();
//        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/");
//        Assert.assertEquals(driver.findElement(By.cssSelector("h3")).getText(),"Epic sadface: Username and password do not match any user in this service");
//    }
//
//    @Test
//    public void loginUsingWrongPassword(){
//        driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
//        driver.findElement(By.cssSelector("#password")).sendKeys("secret_sauce2");
//        driver.findElement(By.cssSelector("#login-button")).click();
//        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/");
//        Assert.assertEquals(driver.findElement(By.cssSelector("h3")).getText(),"Epic sadface: Username and password do not match any user in this service");
//    }
//
//    @Test
//    public void loginUsingWrongCredentials(){
//        driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user1");
//        driver.findElement(By.cssSelector("#password")).sendKeys("secret_sauce2");
//        driver.findElement(By.cssSelector("#login-button")).click();
//        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/");
//        Assert.assertEquals(driver.findElement(By.cssSelector("h3")).getText(),"Epic sadface: Username and password do not match any user in this service");
//    }
}

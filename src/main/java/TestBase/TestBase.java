package TestBase;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class TestBase {
    public static WebDriver driver;
    public static Properties data;

    @BeforeClass
    public void setup(){
        String filePath = System.getProperty("user.dir") + "/src/main/java/Config/Config.properties";
        data = new Properties();
        try{
            data.load(new FileInputStream(filePath));
        }catch(FileNotFoundException f){
            f.printStackTrace();
        }catch(IOException i){
            i.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @BeforeMethod
    public void startBrowser(){
        switch (data.getProperty("Browser").toUpperCase()){
            case "CHROME" :
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/Drivers/chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "FIREFOX": break;

            case "IE":
        }
    }


    @AfterMethod
    public void closeBrowser(){
        driver.close();
    }

    @AfterClass
    public void endSetup (){

    }

    public WebDriver launchApplication(){
        driver.get(data.getProperty("URL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);


        return driver;
    }

    public WebElement waitForElement(WebElement ele){
        System.out.println("Waiting for element: " + ele);
        WebDriverWait wait = new WebDriverWait(driver, 45);
        return wait.until(ExpectedConditions.visibilityOf(ele));
    }

    public List<WebElement> waitForElements(List<WebElement> ele){
        System.out.println("Waiting for element: " + ele);
        WebDriverWait wait = new WebDriverWait(driver, 45);
        return wait.until(ExpectedConditions.visibilityOfAllElements(ele));
    }

    public WebElement applyFluentWait(WebElement ele){
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(60, TimeUnit.SECONDS)
                .pollingEvery(5, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);

        WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return ele;
            }
        });

        return ele;
    }
}

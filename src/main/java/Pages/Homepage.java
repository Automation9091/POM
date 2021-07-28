package Pages;

import TestBase.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Homepage extends TestBase {

    WebDriver driver;

    @FindAll( @FindBy(xpath="//div[@class='sw-main-title']"))
    List<WebElement> Label_Home;


    public Homepage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean verifyHomePage(String label){
        boolean bln = false;
        applyFluentWait(Label_Home.get(0));
        if (waitForElements(Label_Home).size() > 0){
            if (Label_Home.get(0).getText().equalsIgnoreCase(label)){
                bln = true;
            }
        }
        return bln;
    }
}

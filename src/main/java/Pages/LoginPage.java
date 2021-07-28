package Pages;

import TestBase.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends TestBase {
    WebDriver driver;

    @FindBy(xpath="//input[@id='username']")
    WebElement TextBox_Username;

    @FindBy(xpath = "//input[@id='password']")
    WebElement TextBox_Password;

    @FindBy(xpath = "//input[@id='tp-sign-in']")
    WebElement Button_Login;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterUsername(){
        TextBox_Username.sendKeys(data.getProperty("Username"));
    }

    public void enterPassword(){
        TextBox_Password.sendKeys(data.getProperty("Password"));
    }

    public void clickLogin(){
        Button_Login.click();
    }
}

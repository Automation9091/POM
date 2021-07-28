package TestCases;

import Pages.Homepage;
import Pages.LoginPage;
import TestBase.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginCheck extends TestBase {

    LoginPage lp;
    Homepage hp;

    @Test
    public void LoginToApp(){
        driver = launchApplication();
        lp = new LoginPage(driver);
        lp.enterUsername();
        lp.enterPassword();
        lp.clickLogin();
        try{
            System.out.println("*Waiting for application for load completely*");
            Thread.sleep(10000);
        }catch (Exception e){
            e.printStackTrace();
        }
        hp = new Homepage(driver);
        Assert.assertTrue(hp.verifyHomePage("Welcome to TestProject"));
    }
}

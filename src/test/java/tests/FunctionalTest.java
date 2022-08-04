package tests;

import base.BaseTest;
import org.openqa.selenium.Point;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ChatHistoryPage;
import pages.LoginPage;

public class FunctionalTest extends BaseTest {

    @Test
    public void LoginTest1(){
        getDriver().manage().window().setPosition(new Point(50,10));
        LoginPage lp = new LoginPage(getDriver());
        ChatHistoryPage ch = lp.doValidLogin("hello");


    }

    @Test
    public void loginTest2(){
        getDriver().manage().window().setPosition(new Point(600,10));
        LoginPage lp = new LoginPage(getDriver());
        ChatHistoryPage ch = lp.doDefaultUserLogin();
        Assert.assertEquals(ch.accountName(),user2,"Wrong user login");
    }
}

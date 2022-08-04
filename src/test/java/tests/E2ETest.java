package tests;

import base.BaseTest;
import org.openqa.selenium.Point;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ChatHistoryPage;
import pages.ChatRoomPage;
import pages.LoginPage;

public class E2ETest extends BaseTest {

    @Test
    public void chatTest1(){
        getDriver().manage().window().setPosition(new Point(50,10));
        LoginPage lp = new LoginPage(getDriver());
        ChatHistoryPage ch = lp.doValidLogin(user1);
        Assert.assertEquals(ch.accountName(),user1,"Wrong user login");
        ChatRoomPage cr = ch.selectUserToChat(user2);
        cr.sendMessage("Any update?");
        Assert.assertEquals(cr.checkMessage(),"Any update?","Different message has been written.");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void chatTest2(){
        getDriver().manage().window().setPosition(new Point(600,10));
        LoginPage lp = new LoginPage(getDriver());
        ChatHistoryPage ch = lp.doValidLogin(user2);
        Assert.assertEquals(ch.accountName(),user2,"Wrong user login");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ChatRoomPage cr = ch.selectUserToChat(user1);
        cr.sendMessage("Here is the file.");
        Assert.assertEquals(cr.checkMessage(),"Here is the file.","Different message has been written.");
        cr.addAttachment(file.getAbsolutePath());
        Assert.assertTrue(cr.uploadCompleted(fileName));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}

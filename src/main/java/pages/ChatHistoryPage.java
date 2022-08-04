package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.util.List;

public class ChatHistoryPage extends BasePage {
    // Page URL
    private static String endpoint = "/LetsTalk/ContactList";

    // Locators
    By headerTitle = By.xpath("//div[@id='b1-Title']");
    By userSelection = By.xpath("//div[@class='list-item-content-center']//*[contains(@id, 'Title')]");



    // *********Constructor*********
    public ChatHistoryPage(WebDriver driver) {
        super(driver);
        //Assert.assertEquals();
        //wait.until(driver.getCurrentUrl().equals(readText))
        textDisplayed(headerTitle ,"Chat History");
        //Assert.assertEquals(readText(headerTitle), "Chat History");
    }

    public String accountName(){
        By userinfo =By.xpath("//div[@class='user-info']//span");
        return getTextContent(userinfo);

    }

    public ChatRoomPage selectUserToChat(String user)  {
        List<WebElement> elements = driver.findElements(userSelection);
        //System.out.println("number of elements: " + elements.size());

        for(WebElement ele : elements){

            //System.out.println(ele.getText());
            if (ele.getText().contains(user)){
                ele.click();
                break;
            }
        }
        return new ChatRoomPage(driver);
    }

}

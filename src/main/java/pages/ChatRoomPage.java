package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ChatRoomPage extends BasePage {
    // Page URL
    private static String endpoint = "/";

    // Locators
    By textField = By.xpath("//input[@id='Input_TextVar']");
    By submitBtn = By.xpath("//button[@type='submit']");
    By fileUpload = By.xpath("//input[@type='file']");


    public ChatRoomPage(WebDriver driver) {
        super(driver);
    }


    public void sendMessage(String text){
        writeText(textField,text);
    }

    public String checkMessage(){
        return getTextValue(textField);
    }
    public void addAttachment(String filePath){
        justSendKeys(fileUpload,filePath);
    }

    public Boolean uploadCompleted(String fileName){
        By attachment =By.xpath("//div[@id='ViewAttachment']/span[text()='"+fileName+"']");
        return eleDisplayed(attachment);
    }


}

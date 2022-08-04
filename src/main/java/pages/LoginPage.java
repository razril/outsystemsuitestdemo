package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    // Page URL
    private static String endpoint ="/LetsTalk/";

    // Locators
    By userDropDown = By.xpath("//select[@id='Dropdown1']");
    By loginBtn = By.xpath("//button[@type='submit']");



    // *********Constructor*********
    public LoginPage(WebDriver driver) {
        super(driver);
        navigateTo(endpoint);
    }

        public ChatHistoryPage doValidLogin(String username) {
            // Enter Username
            select(userDropDown, username);
            // Click Login Button
            click(loginBtn);
            return new ChatHistoryPage(driver);
        }

    public ChatHistoryPage doDefaultUserLogin() {
        // Click Login Button
        click(loginBtn);
        return new ChatHistoryPage(driver);
    }


}

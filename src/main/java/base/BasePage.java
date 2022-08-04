package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.ConfigManager;

import java.time.Duration;
import java.util.List;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void navigateTo(String endPoint){
        driver.get(ConfigManager.getInstance().getBaseUrl() + endPoint);
    }

    //Wait Element Visibility Wrapper
    public WebElement waitVisibility(By elementBy) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(elementBy));
    }

    //Wait Element Clickable Wrapper
    public WebElement waitClickable(By elementBy) {
        return wait.until(ExpectedConditions.elementToBeClickable(elementBy));
    }

    //Wait Text Displayed Wrapper
    public void textDisplayed(By elementBy,String text){
        wait.until(new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver driver) {
                return driver.findElement(elementBy).getText().contains(text);
            }
        });
    }


    //Wait URL change
    public void URLChanged(String expectedUrl) {

        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return driver.getCurrentUrl().contains(expectedUrl);
            }

        });
    }

    //Click Method
    public void click(By elementBy) {
        waitClickable(elementBy).click();
    }
    //Write Text
    public void writeText(By elementBy, String text) {
        //waitVisibility(elementBy).clear(); --> cause Element Not Interactable Exception
        waitVisibility(elementBy).sendKeys(text);
    }
    //Read Text
    public String readText(By elementBy) {
        return waitVisibility(elementBy).getText();
    }
    //Get value attribute
    public String getTextValue(By elementBy){
        return waitVisibility(elementBy).getAttribute("value");
    }

    //Get value attribute
    public String getTextContent(By elementBy){
        return driver.findElement(elementBy).getAttribute("textContent");
    }

    //Sendkeys
    public void justSendKeys(By elementBy, String text){
        WebElement ele = driver.findElement(elementBy);
        ele.sendKeys(text);
    }
    //Select DropDown
    public void select(By elementBy, String username){
        Select oSelect = new Select(waitVisibility(elementBy).findElement(elementBy));
        //oSelect.deselectAll(); //java.lang.UnsupportedOperationException: You may only deselect all options of a multi-select

        List<WebElement> options = oSelect.getOptions();
        int size = options.size();

        boolean userExist = false;

        for (int i = 0; i < size; i++) {
            //System.out.println(option.getText());

            if (options.get(i).getText().contains(username)) {
                oSelect.selectByIndex(i);
                userExist = true;
                break;
            }

        }

        try {
            if(!userExist) {

                oSelect.selectByValue(username);
            }
        }catch (Exception e) {
            throw new RuntimeException("User not found. " + e);
        }

        //oSelect.selectByVisibleText(username);
        //oSelect.selectByIndex(0);
    }

    //Method for assertion usage
    public Boolean eleDisplayed(By elementBy) {
        boolean displayed;

        try {
            displayed = driver.findElement(elementBy).isDisplayed();
            ;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return displayed;
    }


}

package webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverManager extends DriverManagerAbstract {

    @Override
    protected void startDriver() {
        WebDriverManager.firefoxdriver().cachePath("drivers").setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();

    }
}

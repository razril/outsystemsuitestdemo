package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utilities.ConfigManager;
import utilities.ExtentReportManager;
import webdriver.DriverManager;
import webdriver.DriverManagerAbstract;

import java.io.File;
import java.time.Duration;


public class BaseTest {

    private final ThreadLocal<DriverManagerAbstract> driverManager = new ThreadLocal<>();
    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    protected String user1;
    protected String user2;
    protected File file;
    protected String fileName;

    private void setDriverManager(DriverManagerAbstract driverManager){
        this.driverManager.set(driverManager);
    }

    protected DriverManagerAbstract getDriverManager(){
        return this.driverManager.get();
    }

    protected void setDriver(WebDriver driver){
        this.driver.set(driver);
    }
    protected WebDriver getDriver () {
        return this.driver.get();
    }



    @Parameters({"browser"})
    @BeforeMethod
    public synchronized void startDriver(@Optional("chrome") String browser){
        browser = System.getProperty("browser", browser);
        //System.out.println(browser);
        setDriverManager(DriverManager.getManager(browser));
        setDriver(getDriverManager().getDriver());
        System.out.println("CURRENT THREAD: " + Thread.currentThread().getId() + ", " +
                "DRIVER = " + getDriver());

        //Get data from properties file

        user1 = ConfigManager.getInstance().getUserOne();
        user2 = ConfigManager.getInstance().getUserTwo();

        //System.out.println(user1);

        //Prepare file for test
       file = new File("src/test/resources/bestofluck.png");
       fileName = "bestofluck.png";
    }


    @AfterMethod
    public synchronized void tearDown() throws InterruptedException {
        System.out.println("CURRENT THREAD: " + Thread.currentThread().getId() + ", " +
                "DRIVER = " + getDriver());
        getDriverManager().getDriver().quit();

    }


    public void waitForPageToLoad() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        wait.until(new ExpectedCondition<Boolean>() {

            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) getDriver()).executeScript("return document.readyState").equals("complete");
            }
        });
    }
}

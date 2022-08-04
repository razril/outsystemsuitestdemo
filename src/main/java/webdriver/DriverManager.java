package webdriver;

public class DriverManager {
    public static DriverManagerAbstract getManager(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome" -> {
                return new ChromeDriverManager();
            }
            case "firefox" -> {
                return new FirefoxDriverManager();
            }
            default -> throw new IllegalStateException("Unexpected value: " + browser);
        }
    }
}

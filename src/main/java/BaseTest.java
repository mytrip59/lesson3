import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public abstract class BaseTest {
    private WebDriver getWebDriver() {
        String browser = org.openqa.selenium.Properties.getBrowser();



        return webDriver;
    }
}

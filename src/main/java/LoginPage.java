import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    WebDriver webDriver;

    private By emailInputSelector = By.id("email");
    private By passwordInputSelector = By.id("passwd");
    private By submitFormSelector = By.name("submitLogin");
    final private String EMAIL = "webinar.test@gmail.com";
    final private String PASSWORD = "Xcg7299bnSmMuRLp9ITw";


    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void open (){
        webDriver.get(Properties.getBaseAdminUrl());
    }

    public void fillEmailInput (){
        WebElement webElementEmailInput = webDriver.findElement(emailInputSelector);
        webElementEmailInput.sendKeys(EMAIL);
    }

    public void fillPasswordInput (){
        WebElement webElementPasswordInput = webDriver.findElement(passwordInputSelector);
        webElementPasswordInput.sendKeys(PASSWORD);
    }

    public void clickSubmitButton(){
        WebElement submitButton = webDriver.findElement(submitFormSelector);
        submitButton.click();
    }

}

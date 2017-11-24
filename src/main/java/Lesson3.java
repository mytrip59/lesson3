import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

public class Lesson3 {
    public static void main (String [] args){
        WebDriver webDriver = BaseScript.getConfiguredDriver();
        try {
            LoginPage loginPage = new LoginPage(webDriver);
            loginPage.open();

            loginPage.fillEmailInput();
            loginPage.fillPasswordInput();
            loginPage.clickSubmitButton();
            loginPage.waitLoadingLoginPage();

            CatalogPage catalogPage = new CatalogPage(webDriver);
            catalogPage.clickCategoryMenu();
            catalogPage.waitLoadingCategoryPage();
            catalogPage.clickAddCategory();
            catalogPage.fillCategoryName();
            catalogPage.saveNewCategory();
            catalogPage.waitLoadingCategoryPage();
            catalogPage.checkCreateNewCategory();

        } catch (Exception e){
            BaseScript.quiteDriver(webDriver);
        }
        BaseScript.quiteDriver(webDriver);

        // login

        // create category

        // check that new category appears in Categories table

        // finish script


/*
        Lesson3 lesson3 = new Lesson3();
        lesson3.threadSleep(3000);*/

/*
            etalonTitle = lesson3.clickMenueAndGetTitle(driver, menuName, changingTitleName);
            driver.navigate().refresh();
            lesson3.threadSleep(1000);
            afterRefreshTitle = lesson3.getOnlyTitle(driver, changingTitleName);

            if (etalonTitle.equals(afterRefreshTitle)) {
                System.out.println("The title for " + numberMenuItem +  " menu item is " + etalonTitle + ". This title is equivalent to itself after refresh.");
            } else System.out.println("The title for " + numberMenuItem +  " menu item is " + etalonTitle + ".  This title is NOT equivalent to itself after refresh. Title after refresh is " + afterRefreshTitle + ".");
*/

    }

    public WebDriver initChrome(){
/*        URL url = getClass().getClassLoader().getResource ("chromedriver.exe");
        System.setProperty("webdriver.chrome.driver",url.getFile());*/

/*        URL url = Lesson3.class.getResource ("chromedriver.exe");
        System.setProperty("webdriver.chrome.driver",url.getFile());
        */
        System.setProperty("webdriver.chrome.driver",
                new File(Lesson3.class.getResource("chromedriver.exe").getFile()).getPath());
        return new ChromeDriver();
    }

    public void enterPassword (WebDriver driver, String logField, String pasField){
        WebElement login = driver.findElement(By.id("email"));
        login.sendKeys(logField);
        WebElement password = driver.findElement(By.id("passwd"));
        password.sendKeys(pasField);
        WebElement button = driver.findElement(By.name ("submitLogin"));
        button.click();
    }

    public String clickMenueAndGetTitle(WebDriver driver, String pathMenueItem, String pathPageTile){
        WebElement webElement = driver.findElement(By.xpath(pathMenueItem));
        webElement.click();
        threadSleep(3000);
        WebElement webElementTitle = driver.findElement(By.xpath(pathPageTile));
        return webElementTitle.getText();

    }

    public String getOnlyTitle (WebDriver driver, String pathPageTile){
        WebElement webElementTitle = driver.findElement(By.xpath(pathPageTile));
        return webElementTitle.getText();

    }


    public static void threadSleep(int waitTimeMillisec){
        try {
            Thread.sleep(waitTimeMillisec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void exitPage (WebDriver driver){
        WebElement webElement = driver.findElement(By.xpath(".//*[@id='employee_infos']/a"));
        webElement.click();
        WebElement exit = driver.findElement(By.xpath(".//*[@id='header_logout']"));
        exit.click();
    }

}
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Lesson3 {
    public static final String CONTENT_TITLE = ".//*[@id='content']/div[1]/div/h2";

    // only for MODULES and for CATALOGUE
    public static final String MAIN_DIV_TITLE = ".//*[@id='main-div']/div[1]/h2";

    public static final String DASHBOARD = ".//*[@id='tab-AdminDashboard']/a";
    public static final String ORDERS = ".//*[@id='subtab-AdminParentOrders']/a";
    public static final String CATALOGUE = ".//*[@id='subtab-AdminCatalog']/a";
    public static final String CLIENTS = "/html/body/nav/ul/li[5]/a";
    public static final String CLIENTSERVICE = ".//*[@id='subtab-AdminParentCustomerThreads']/a";
    public static final String STATISTIC = ".//*[@id='subtab-AdminStats']/a";
    public static final String MODULES = ".//*[@id='subtab-AdminParentModulesSf']/a";
    public static final String DESIGN = "/html/body/nav/ul/li[10]/a";
    //public static final String DESIGN = ".//*[@id='subtab-AdminParentThemes']/a";
    public static final String DELIVERY = ".//*[@id='subtab-AdminParentShipping']/a";
    public static final String PAYMENT = ".//*[@id='subtab-AdminParentPayment']/a";
    public static final String INTERNATIONAL = ".//*[@id='subtab-AdminInternational']/a";
    public static final String SHOPPARAMETERS = ".//*[@id='subtab-ShopParameters']/a";
    public static final String ADMINADVANCEDPARAMETERS = ".//*[@id='subtab-AdminAdvancedParameters']/a";

    public static void main (String [] args){
        Lesson3 lesson3 = new Lesson3();
        List<String> menuLinksArray = new ArrayList<String>();
        String etalonTitle;
        String afterRefreshTitle;
        menuLinksArray.add(DASHBOARD);
        menuLinksArray.add(ORDERS);
        menuLinksArray.add(CATALOGUE);
        menuLinksArray.add(CLIENTS);
        menuLinksArray.add(CLIENTSERVICE);
        menuLinksArray.add(STATISTIC);
        menuLinksArray.add(MODULES);
        menuLinksArray.add(DESIGN);
        menuLinksArray.add(DELIVERY);
        menuLinksArray.add(PAYMENT);
        menuLinksArray.add(INTERNATIONAL);
        menuLinksArray.add(SHOPPARAMETERS);
        menuLinksArray.add(ADMINADVANCEDPARAMETERS);

        WebDriver driver = lesson3.initChrome();
        driver.get("http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/");
        lesson3.enterPassword (driver, "webinar.test@gmail.com", "Xcg7299bnSmMuRLp9ITw");
        lesson3.threadSleep(3000);

        int numberMenuItem = 1;
        for (String menuName : menuLinksArray) {
            String changingTitleName;

            if (menuName.equals(MODULES) | menuName.equals(CATALOGUE)) {
                changingTitleName = MAIN_DIV_TITLE;
            } else {
                changingTitleName = CONTENT_TITLE;
            }

            etalonTitle = lesson3.clickMenueAndGetTitle(driver, menuName, changingTitleName);
            driver.navigate().refresh();
            lesson3.threadSleep(1000);
            afterRefreshTitle = lesson3.getOnlyTitle(driver, changingTitleName);

            if (etalonTitle.equals(afterRefreshTitle)) {
                System.out.println("The title for " + numberMenuItem +  " menu item is " + etalonTitle + ". This title is equivalent to itself after refresh.");
            } else System.out.println("The title for " + numberMenuItem +  " menu item is " + etalonTitle + ".  This title is NOT equivalent to itself after refresh. Title after refresh is " + afterRefreshTitle + ".");

            numberMenuItem++;
        }
        lesson3.exitPage(driver);
        driver.quit();
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

    public String clickMenueAndGetTitle(WebDriver driver, String pathMenueItem, String pathTile){
        WebElement webElement = driver.findElement(By.xpath(pathMenueItem));
        webElement.click();
        threadSleep(3000);
        WebElement webElementTitle = driver.findElement(By.xpath(pathTile));
        return webElementTitle.getText();

    }

    public String getOnlyTitle (WebDriver driver, String pathTile){
        WebElement webElementTitle = driver.findElement(By.xpath(pathTile));
        return webElementTitle.getText();

    }


    public void threadSleep(int waitTimeMilisec){
        try {
            Thread.sleep(waitTimeMilisec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void exitPage (WebDriver driver){
        WebElement webElement = driver.findElement(By.xpath(".//*[@id='employee_infos']/a"));
        webElement.click();
        WebElement exit = driver.findElement(By.xpath(".//*[@id='header_logout']"));
        exit.click();
    }

}
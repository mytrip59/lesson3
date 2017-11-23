import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class CatalogPage {
    WebDriver webDriver;
    // private static final String CATALOGUE = ".//*[@id='subtab-AdminCatalog']/a";
    // not work ul[class=main-menu] a[href*=catalog]
    private By catalogLinkSelector = By.cssSelector("ul[class=menu] a[href*=catalog]");
    //not work li[class*=link-leveltwo]>a[href*=AdminCategories]
    private By categoryLinkSelector = By.cssSelector("#subtab-AdminCategories>a");

    public CatalogPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }



/*    public void clickCatalogLink (){
        WebElement webElement = webDriver.findElement(catalogLinkSelector);
        webElement.click();
    }*/

    public void clickCategoryMenu (){
        Actions mouseAction = new Actions(webDriver);
        WebElement webElementCatalogLink = webDriver.findElement(catalogLinkSelector);
        WebElement webElementCategoryLink = webDriver.findElement(categoryLinkSelector);
        mouseAction.moveToElement(webElementCatalogLink).build().perform();
        Lesson3.threadSleep(3000);
        mouseAction.moveToElement(webElementCategoryLink).click().build().perform();
        Lesson3.threadSleep(3000);
    }

}

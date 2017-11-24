import org.junit.internal.ExactComparisonCriteria;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedCondition.*;

public class CatalogPage {
    //        Lesson3.threadSleep(3000);
    private WebDriver webDriver;

    // private static final String CATALOGUE = ".//*[@id='subtab-AdminCatalog']/a";
    // not work ul[class=main-menu] a[href*=catalog]
    private By catalogLinkSelector = By.cssSelector("ul[class=menu] a[href*=catalog]");
    //not work li[class*=link-leveltwo]>a[href*=AdminCategories]
    private By categoryLinkSelector = By.cssSelector("#subtab-AdminCategories>a");
    private By addCategoryLinkSelector = By.cssSelector("a#page-header-desc-category-new_category");

    public CatalogPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void clickCategoryMenu (){
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(addCategoryLinkSelector));
        Actions mouseAction = new Actions(webDriver);
        WebElement webElementCatalogLink = webDriver.findElement(catalogLinkSelector);
        WebElement webElementCategoryLink = webDriver.findElement(categoryLinkSelector);
        mouseAction.moveToElement(webElementCatalogLink).build().perform();
        mouseAction.moveToElement(webElementCategoryLink).click().build().perform();
   }

    public void clickAddCategory (){
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(addCategoryLinkSelector));
        WebElement webElementaddCategoryLink = webDriver.findElement(addCategoryLinkSelector);
        webElementaddCategoryLink.click();


    }

}

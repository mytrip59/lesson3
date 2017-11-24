import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CatalogPage {
    //        Lesson3.threadSleep(3000);
    private WebDriver webDriver;
    private final String NEWCATEGORY = "Handmade clothes";

    // private static final String CATALOGUE = ".//*[@id='subtab-AdminCatalog']/a";
    // not work ul[class=main-menu] a[href*=catalog]
    private By catalogLinkSelector = By.cssSelector("ul[class=menu] a[href*=catalog]");
    //not work li[class*=link-leveltwo]>a[href*=AdminCategories]
    private By categoryLinkSelector = By.cssSelector("#subtab-AdminCategories>a");
    private By addCategoryLinkSelector = By.cssSelector("a#page-header-desc-category-new_category");
    // 1 selector in the bottom of the page for waiting of loading page
    private By buttonGroupActionsSelector = By.cssSelector("#form-category > div > div.panel-heading");
    private By alertSuccessfulNewCategorySelector = By.cssSelector("#content div[class='alert alert-success']");



// Page - new category
    private By categoryNameInputSelector = By.cssSelector("#name_1");
    // selector in the bottom of the page for waiting of loading page
    private By buttonSaveNewCategorySelector = By.cssSelector("#category_form_submit_btn > i");




    public CatalogPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void clickCategoryMenu (){
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(catalogLinkSelector));
        Actions mouseAction = new Actions(webDriver);
        WebElement webElementCatalogLink = webDriver.findElement(catalogLinkSelector);
        mouseAction.moveToElement(webElementCatalogLink).build().perform();

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(categoryLinkSelector));
        WebElement webElementCategoryLink = webDriver.findElement(categoryLinkSelector);
        mouseAction.moveToElement(webElementCategoryLink).click().build().perform();
   }

    public void clickAddCategory (){
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(addCategoryLinkSelector));
        WebElement webElementaddCategoryLink = webDriver.findElement(addCategoryLinkSelector);
        webElementaddCategoryLink.click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(buttonSaveNewCategorySelector));
    }

    public void fillCategoryName (){
        WebElement webElementEmailInput = webDriver.findElement(categoryNameInputSelector);
        webElementEmailInput.sendKeys(NEWCATEGORY);
    }

    public void saveNewCategory (){
        WebElement webElementSaveNewCategoryLink = webDriver.findElement(buttonSaveNewCategorySelector);
        webElementSaveNewCategoryLink.click();
    }

    public void waitLoadingCategoryPage (){
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(buttonGroupActionsSelector));
    }

    public void checkCreateNewCategory(){
        try {
            WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
            webDriverWait.until(ExpectedConditions.elementToBeClickable(alertSuccessfulNewCategorySelector));
            System.out.println("New category was created successfully!");
        } catch (Exception e) {
            System.out.println("Warning! New category was not created!");
        }
    }

}

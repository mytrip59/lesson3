import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CatalogPage {
    private WebDriver webDriver;

    // Menu panel
    private By catalogLinkSelector = By.cssSelector("ul[class=menu] a[href*=catalog]");
    private By categoryLinkSelector = By.cssSelector("#subtab-AdminCategories>a");

    // category page
    private By addCategoryLinkSelector = By.cssSelector("a#page-header-desc-category-new_category");
    private By alertSuccessfulNewCategorySelector = By.cssSelector("#content div[class='alert alert-success']");

    //Filter panel
    private By searchNameFilterSelector = By.cssSelector("input[name='categoryFilter_name']");
    private By searchButtonFilterSelector = By.cssSelector("button[id=submitFilterButtoncategory]");
    private By resultNumberLinesFilterSelector = By.cssSelector("div[class='panel-heading']>span");
    private By countLinesInFilterSelector = By.cssSelector("table#table-category>tbody>tr");

    // 1 selector in the bottom of the page for waiting of loading page
    private By buttonGroupActionsSelector = By.cssSelector("#form-category > div > div.panel-heading");

    // new category subpage
    private By categoryNameInputSelector = By.cssSelector("#name_1");
    // selector in the bottom of the page for waiting of loading page
    private By buttonSaveNewCategorySelector = By.cssSelector("#category_form_submit_btn > i");


    public CatalogPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void clickCategoryMenu() {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(catalogLinkSelector));
        Actions mouseAction = new Actions(webDriver);
        WebElement webElementCatalogLink = webDriver.findElement(catalogLinkSelector);
        mouseAction.moveToElement(webElementCatalogLink).build().perform();

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(categoryLinkSelector));
        WebElement webElementCategoryLink = webDriver.findElement(categoryLinkSelector);
        mouseAction.moveToElement(webElementCategoryLink).click().build().perform();
    }

    public void clickAddCategory() {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(addCategoryLinkSelector));
        WebElement webElementaddCategoryLink = webDriver.findElement(addCategoryLinkSelector);
        webElementaddCategoryLink.click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(buttonSaveNewCategorySelector));
    }

    public void fillCategoryName(String newCategoryName) {
        WebElement webElementEmailInput = webDriver.findElement(categoryNameInputSelector);
        webElementEmailInput.sendKeys(newCategoryName);
    }

    public void saveNewCategory() {
        WebElement webElementSaveNewCategoryLink = webDriver.findElement(buttonSaveNewCategorySelector);
        webElementSaveNewCategoryLink.click();
    }

    public void waitLoadingCategoryPage() {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(buttonGroupActionsSelector));
    }

    public void fillSearchNameFilterInput(String newCategoryName) {
        WebElement webElementsearchNameFilterInput = webDriver.findElement(searchNameFilterSelector);
        webElementsearchNameFilterInput.sendKeys(newCategoryName);
    }

    public void clickSearchButtonFilter() {
        WebElement submitButton = webDriver.findElement(searchButtonFilterSelector);
        submitButton.click();
    }

    public boolean checkCreateNewCategoryAlert(String newCategoryName) {
        try {
            WebDriverWait webDriverWait = new WebDriverWait(webDriver, 3);
            webDriverWait.until(ExpectedConditions.elementToBeClickable(alertSuccessfulNewCategorySelector));
            System.out.println("**** Alert! New category was created successfully! ****");
            return true;
            // save number of categories with new name
        } catch (Exception e) {
            System.out.println("**** Successful alert, that new category was created, was not displayed! ****");
            BaseScript.quiteDriver(webDriver);
            System.exit(1);
            return false;

        }
    }

    public int checkUniqueCategoryFilter(String newCategoryName) {
        if (getNumberofLines().equals("empty")){
            return 0;
        }
        int countOfNewCategoryName = 0;
        // check list of categories
        By allCategroriesNamesFilterSelector = By.cssSelector("table#table-category>tbody>tr>td:nth-child(" + getNumberofLines() + ")");

        List<WebElement> oneCategoryFilterElement = webDriver.findElements(allCategroriesNamesFilterSelector);
        for (WebElement e : oneCategoryFilterElement) {
            if (e.getText().equals(newCategoryName)) {
                countOfNewCategoryName++;
            }
        }
        // Category was met only one time
        if (countOfNewCategoryName == 1) {
            return 1;
            // Category was met more than one time
        } else if (countOfNewCategoryName > 1) {
            return 2;
            // Category was NOT met
        } else {
            return 0;
        }
    }

    // check the count of line in filter.
    String getNumberofLines (){
        int countOfLines =webDriver.findElements(countLinesInFilterSelector).size();
        String numberOfChildsInSelector = null;
        if (countOfLines == 0){
            //List if empty
            numberOfChildsInSelector = "empty";
        } else if (countOfLines == 1){
            numberOfChildsInSelector = "2";
        } else if (countOfLines > 1){
            numberOfChildsInSelector = "3";
        }
        return numberOfChildsInSelector;
    }
}

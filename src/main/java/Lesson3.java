import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class Lesson3 {
    private final String NEWCATEGORY = "Handmade";

    public static void main (String [] args) {
        Lesson3 lesson3 = new Lesson3();

        EventFiringWebDriver webDriver = BaseScript.getConfiguredDriver();
        try {
            LoginPage loginPage = new LoginPage(webDriver);
            loginPage.open();

            loginPage.fillEmailInput();
            loginPage.fillPasswordInput();
            loginPage.clickSubmitButton();
            loginPage.waitLoadingLoginPage();

            CatalogPage catalogPage = new CatalogPage(webDriver);
            //click menu
            catalogPage.clickCategoryMenu();
            catalogPage.waitLoadingCategoryPage();

            // check that new category is unique
            int intCheckUniqueCategoryFilter = catalogPage.checkUniqueCategoryFilter(lesson3.NEWCATEGORY);
            if (intCheckUniqueCategoryFilter > 0) {
                System.out.println("**** Category " + lesson3.NEWCATEGORY + " you want to create, is alredy exist. " +
                        "Please, change the name of a new category or use existing category. ****");
                BaseScript.quiteDriver(webDriver);
                System.exit(1);
            }
            ;

            // add new category
            catalogPage.clickAddCategory();
            catalogPage.fillCategoryName(lesson3.NEWCATEGORY);
            catalogPage.saveNewCategory();
            catalogPage.waitLoadingCategoryPage();
            catalogPage.checkCreateNewCategoryAlert(lesson3.NEWCATEGORY);

            // second check of the created category in the filer list
            intCheckUniqueCategoryFilter = catalogPage.checkUniqueCategoryFilter(lesson3.NEWCATEGORY);
            if (intCheckUniqueCategoryFilter == 1) {
                System.out.println("**** Category " + lesson3.NEWCATEGORY + " was filtered successfully! ****");
            } else if (intCheckUniqueCategoryFilter == 0) {
                System.out.println("**** Warning! Category " + lesson3.NEWCATEGORY + " you want to create, was not created! ****");
                BaseScript.quiteDriver(webDriver);
                System.exit(1);
            } else {
                System.out.println("**** Warning! Category " + lesson3.NEWCATEGORY + " is not unique after creating! " +
                        "Please, change the name of a new category or use existing category. ****");
                BaseScript.quiteDriver(webDriver);
                System.exit(1);
            }

            // filtr new category
            catalogPage.fillSearchNameFilterInput(lesson3.NEWCATEGORY);
            catalogPage.clickSearchButtonFilter();


        } catch (Exception e) {
            BaseScript.quiteDriver(webDriver);
        }
        BaseScript.quiteDriver(webDriver);
    }

}
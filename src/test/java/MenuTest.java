import com.zebrunner.carina.core.IAbstractTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import web.components.Menu;
import web.pages.common.HomePageBase;
import web.pages.common.SearchResultsPageBase;

public class MenuTest implements IAbstractTest {

    @Test
    public void menuFunctionTest() {
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open(false);
        Menu menu = homePage.getHeader().getMenu();
        String menuItem = "Electronics";
        menu.openMenuItem(menuItem);
        Assert.assertTrue(menu.isMenuTitlePresent(menuItem), "The title isn't as expected!");
        menu.goBackToMainMenu();
        Assert.assertTrue(menu.isMenuItemsPresent(), "Main manu isn't opened!");
    }

    @Test
    public void subMenuFunctionTest() {
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open(false);
        Menu menu = homePage.getHeader().getMenu();
        String menuItem = "Electronics";
        String subMenuItem = "Headphones";
        menu.openMenuItem(menuItem);
        SearchResultsPageBase searchResultsPage = menu.openSubMenuItem(subMenuItem);
        Assert.assertTrue(searchResultsPage.getProductCategory().contains(subMenuItem),
                "Product Category isn't right!");
    }
}

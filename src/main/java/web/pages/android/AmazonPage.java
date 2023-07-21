package web.pages.android;

import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import web.components.Menu;
import web.pages.common.AmazonPageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = AmazonPageBase.class)
public class AmazonPage extends AmazonPageBase {

    @FindBy(id = "nav-logo-sprites")
    private ExtendedWebElement logo;

    @FindBy(id = "nav-search-keywords")
    private ExtendedWebElement searchField;

    @FindBy(xpath = "//input[@type='submit']")
    private ExtendedWebElement searchButton;

    @FindBy(id = "nav-button-cart")
    private ExtendedWebElement cartButton;

    @FindBy(id = "nav-hamburger-menu")
    private ExtendedWebElement navHamburger;

    @FindBy(id = "hmenu-header-account-text")
    private ExtendedWebElement signInField;

    @FindBy(id = "hmenu-canvas")
    private Menu menu;

    public AmazonPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAmazonPageOpened() {
        return logo.isElementPresent();
    }

    @Override
    public void searchGoods(String searchText) {
        searchField.type(searchText);
        searchButton.click();
    }

    @Override
    public void openCart() {
        cartButton.click();
    }

    @Override
    public void openNavItem(String itemName) {
        navHamburger.click();
        menu.openMenuItem(itemName);
    }
}

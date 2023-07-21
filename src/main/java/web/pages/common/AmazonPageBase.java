package web.pages.common;

import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class AmazonPageBase extends AbstractPage {

    public AmazonPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract boolean isAmazonPageOpened();

    public abstract void searchGoods(String searchText);

    public abstract void openCart();

    public abstract void openNavItem(String itemName);
}

package web.components.desktop;

import com.zebrunner.carina.utils.factory.ICustomTypePageFactory;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import web.pages.common.ProductPageBase;

public class ProductCard extends AbstractUIObject implements ICustomTypePageFactory {

    @FindBy(xpath = ".//h2")
    private ExtendedWebElement productTitle;

    @FindBy(xpath = ".//span[contains(text(), 'stars')]")
    private ExtendedWebElement productRating;

    @FindBy(xpath = ".//span[contains(@class, 'underline')]")
    private ExtendedWebElement reviewsCount;

    @FindBy(xpath = ".//span[@class='a-price']")
    private ExtendedWebElement productPrice;

    public ProductCard(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public boolean isAllInfoPresent() {
        return productPrice.isPresent(1) && productRating.isPresent(1) && productTitle.isPresent(1);
    }

    public String getProductTitle() {
        return productTitle.getText();
    }

    public double getPrice() {
        return Double.parseDouble(productPrice.getText()
                .replaceAll("\\$", "").replaceAll("\n", "."));
    }

    public double getRating() {
        return Double.parseDouble(productRating.getAttribute("innerText").split(" out of")[0]);
    }

    public ProductPageBase openProductPage() {
        productPrice.click();
        return initPage(getDriver(), ProductPageBase.class);
    }
}

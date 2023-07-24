package web.components.desktop;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class ProductCard extends AbstractUIObject {

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

    public double getPrice() {
        return Double.parseDouble(productPrice.getText().replaceAll("\\$", ""));
    }
}

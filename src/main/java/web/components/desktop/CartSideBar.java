package web.components.desktop;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartSideBar extends AbstractUIObject {

    @FindBy(xpath = ".//span[contains(@class,'subtotal')]/span")
    private ExtendedWebElement cartSubTotal;

    @FindBy(xpath = ".//a[contains(text(),'Cart')]")
    private ExtendedWebElement goToCartButton;

    @FindBy(xpath = ".//span[contains(@class,'prompt')] | .//input[@name='quantityBox']")
    private ExtendedWebElement productQuantity;

    @FindBy(xpath = "//input[@title='Delete']")
    private ExtendedWebElement deleteButton;

    public CartSideBar(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public Double getSubtotalAmount() {
        double price = Double.parseDouble(cartSubTotal.getText().replaceAll("\\$", "").replaceAll(",",""));
        String result = String.format("%.2f",price);
        return Double.parseDouble(result);
    }

    public Integer getCartProductQuantity() {
        return Integer.parseInt(productQuantity.getText());
    }

    public void deleteProductFromCart() {
        deleteButton.click();
        waitUntil(ExpectedConditions.invisibilityOf(deleteButton.getElement()), 3);
    }
}

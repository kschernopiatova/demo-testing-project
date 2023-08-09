package com.solvd.demo.project.web.pages.common;

import com.solvd.demo.project.web.components.desktop.CartProduct;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPageBase extends AbstractPage {

    @FindBy(xpath = "//span[contains(@id,'quantity-label')] | //input[@name='quantityBox']")
    private ExtendedWebElement quantityLabel;

    @FindBy(xpath = "//div[contains(@class,'subtotal')]//span[@class='a-price-whole']")
    private ExtendedWebElement wholePrice;

    @FindBy(xpath = "//div[contains(@class,'subtotal')]//span[@class='a-price-fraction']")
    private ExtendedWebElement fractionPrice;

    @FindBy(xpath = "//input[@data-action='delete']")
    private ExtendedWebElement deleteProductButton;

    @FindBy(xpath = "//h2[contains(@class,'amazon-cart-is-empty')]")
    private ExtendedWebElement cartEmptyTitle;

    @FindBy(xpath = "//div[@class='sc-list-item-content']")
    private List<CartProduct> cartProducts;

    public CartPageBase(WebDriver driver) {
        super(driver);
    }

    public Double getSubtotalAmount() {
        String stringPrice = wholePrice.getText().replaceAll(",", "") + "." + fractionPrice.getText();
        return Double.parseDouble(stringPrice);
    }

    public CartProduct getFirstCartProduct() {
        return cartProducts.get(0);
    }

    public boolean isCartEmpty() {
        return cartEmptyTitle.isPresent();
    }
}

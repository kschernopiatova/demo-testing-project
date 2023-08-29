package com.solvd.demo.project.web.pages.ios;

import com.solvd.demo.project.web.pages.common.ProductPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = ProductPageBase.class)
public class ProductPage extends ProductPageBase {

    @FindBy(id = "title")
    private ExtendedWebElement productTitle;

    @FindBy(xpath = "//span[contains(@class,'PriceToPay')]/span[@class='a-offscreen']")
    private ExtendedWebElement iosPrice;

    @FindBy(xpath = "//div[@id='productTitleExpanderRow']//a[@id='acrCustomerReviewLink']/span[1]")
    private ExtendedWebElement anotherRating;

    @FindBy(id = "attach-accessory-card-deck")
    private ExtendedWebElement cartPopup;

    @FindBy(xpath = "//div[@class='sparkle-close']")
    private ExtendedWebElement closeAlertButton;

    @FindBy(id = "NATC_MWEB_FULL_PAGE_CONF_MSG_SUCCESS")
    private ExtendedWebElement addedSuccessfullyTitle;

    @FindBy(xpath = "//span[text()='DONE']")
    private ExtendedWebElement doneButton;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getProductTitle() {
        return productTitle.getText();
    }

    @Override
    public Double getProductPrice() {
        String price = iosPrice.getAttribute("innerText")
                    .replaceAll("\\$", "")
                    .replaceAll(",","");
        return Double.parseDouble(price);
    }

    @Override
    public Double getProductRating() {
        return Double.parseDouble(anotherRating.getText().trim());
    }

    @Override
    public void addProductToCart() {
        closeAlertButton.clickIfPresent();
        addToCartButton.click();
        doneButton.clickIfPresent();
    }

    @Override
    public boolean isCartSidebarPresent() {
        return cartPopup.isPresent() || addedSuccessfullyTitle.isPresent();
    }
}

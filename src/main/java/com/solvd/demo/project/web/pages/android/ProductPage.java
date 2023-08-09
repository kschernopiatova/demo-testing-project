package com.solvd.demo.project.web.pages.android;

import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import com.solvd.demo.project.web.pages.common.ProductPageBase;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = ProductPageBase.class)
public class ProductPage extends ProductPageBase {

    @FindBy(id = "title")
    private ExtendedWebElement productTitle;

    @FindBy(xpath = "//div[@id='corePriceDisplay_mobile_feature_div']//span[contains(@class,'priceToPay')]//span[@class='a-price-whole']")
    private ExtendedWebElement priceWhole;

    @FindBy(xpath = "//div[@id='corePriceDisplay_mobile_feature_div']//span[contains(@class,'priceToPay')]//span[@class='a-price-fraction']")
    private ExtendedWebElement priceFraction;

    @FindBy(xpath = "//div[@id='productTitleExpanderRow']//a[@id='acrCustomerReviewLink']//i/span")
    private ExtendedWebElement productRating;

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
        String price = "";
        try {
            price = priceWhole.getText() + "." + priceFraction.getText();
        } catch (Exception e) {
            price = productPrice.getAttribute("innerText").replaceAll("\\$", "").replaceAll(",","");
        }
        return Double.parseDouble(price);
    }

    @Override
    public Double getProductRating() {
        try {
            return Double.parseDouble(productRating.getText().split(" out of")[0]);
        } catch (Exception e) {
            return Double.parseDouble(anotherRating.getText().trim());
        }
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

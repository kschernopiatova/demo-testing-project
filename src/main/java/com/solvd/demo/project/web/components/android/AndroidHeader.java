package com.solvd.demo.project.web.components.android;

import com.zebrunner.carina.utils.android.AndroidService;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.solvd.demo.project.enums.SearchOptions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import com.solvd.demo.project.web.components.desktop.Header;
import com.solvd.demo.project.web.pages.common.CartPageBase;
import com.solvd.demo.project.web.pages.common.SearchResultsPageBase;

public class AndroidHeader extends Header {

    @FindBy(id = "glow-ingress-single-line")
    private ExtendedWebElement deliveryLocation;

    @FindBy(xpath = "//a[contains(text(),'zip code')]/..")
    private ExtendedWebElement enterZipButton;

    @FindBy(id = "nav-search-keywords")
    private ExtendedWebElement searchField;

    @FindBy(xpath = ".//input[@class='nav-input']")
    private ExtendedWebElement searchButton;

    @FindBy(id = "cart-size")
    private ExtendedWebElement cartButton;

    public AndroidHeader(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    @Override
    public void chooseUSLocation(String zipCode){
        deliveryLocation.click();
        IMobileUtils androidService = new AndroidService();
        androidService.tap(enterZipButton);
        androidService.hideKeyboard();
        zipCodeInput.type(zipCode);
        applyButton.click();
        applyButton.clickIfPresent();
    }

    @Override
    public SearchResultsPageBase openRandomSuggestedGoods() {
        searchField.type(SearchOptions.getRandomSearch());
        searchButton.click();
        SearchResultsPageBase searchResultsPage = initPage(getDriver(), SearchResultsPageBase.class);
        if (searchResultsPage.getFoundProducts().size() == 0)
            searchButton.click();
        return searchResultsPage;
    }

    @Override
    public CartPageBase openCart() {
        cartButton.click();
        return initPage(getDriver(), CartPageBase.class);
    }
}

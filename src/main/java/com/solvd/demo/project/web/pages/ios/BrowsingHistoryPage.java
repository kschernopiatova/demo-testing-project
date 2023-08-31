package com.solvd.demo.project.web.pages.ios;

import com.solvd.demo.project.web.pages.common.BrowsingHistoryPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = BrowsingHistoryPageBase.class)
public class BrowsingHistoryPage extends BrowsingHistoryPageBase {

    @FindBy(xpath = "//h1")
    private ExtendedWebElement title;

    @FindBy(xpath = "//span[@class='a-expander-prompt']")
    private ExtendedWebElement manageHistoryLink;

    @FindBy(id = "toggleClickstreamSwitch")
    private ExtendedWebElement historySwitcherEnabled;

    @FindBy(id = "gridItemRoot")
    private List<ExtendedWebElement> viewedProducts;

    @FindBy(xpath = "//a[@class='a-switch-control']")
    private ExtendedWebElement historySwitcher;

    @FindBy(xpath = "//span[text()='Turn off']")
    private ExtendedWebElement turnOffButton;

    public BrowsingHistoryPage(WebDriver driver) {
        super(driver);
        waitUntil(ExpectedConditions.visibilityOf(title.getElement()), 5);
    }

    @Override
    public void disableHistory() {
        manageHistoryLink.click();
        if (historySwitcherEnabled.getAttribute("checked") != null) {
            historySwitcher.click();
            turnOffButton.clickIfPresent();
        }
    }

    @Override
    public void enableHistory() {
        manageHistoryLink.click();
        if (historySwitcherEnabled.getAttribute("checked") == null) {
            historySwitcher.click();
        }
    }

    @Override
    public Integer getNumberOfViewedProducts() {
        if (!viewedProducts.isEmpty())
            return viewedProducts.size();
        else return 0;
    }
}

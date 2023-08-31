package com.solvd.demo.project.web.pages.ios;

import com.solvd.demo.project.web.components.android.AndroidHeader;
import com.solvd.demo.project.web.components.desktop.Header;
import com.solvd.demo.project.web.components.ios.IosHeader;
import com.solvd.demo.project.web.pages.common.HomePageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = HomePageBase.class)
public class HomePage extends HomePageBase {

    @FindBy(id = "navbar")
    private IosHeader header;

    @FindBy(id = "icp-touch-link-country")
    private ExtendedWebElement chooseCountryLink;

    @FindBy(id = "icp-dropdown")
    private ExtendedWebElement chooseCountrySelector;

    @FindBy(xpath = "//a[text()='Canada']")
    private ExtendedWebElement canadaOption;

    @FindBy(id = "icp-save-button-announce")
    private ExtendedWebElement goWebsiteButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public IosHeader getHeader() {
        return header;
    }
}

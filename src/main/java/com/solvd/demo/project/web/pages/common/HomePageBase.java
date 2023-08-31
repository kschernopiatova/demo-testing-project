package com.solvd.demo.project.web.pages.common;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import com.solvd.demo.project.web.components.desktop.Header;

public abstract class HomePageBase extends AbstractPage {

    @FindBy(id = "navbar")
    protected Header header;

    @FindBy(xpath = "//span[contains(text(),'Browsing History')]")
    private ExtendedWebElement browsingHistoryLink;

    public HomePageBase(WebDriver driver) {
        super(driver);
    }

    public LoginPageBase goToLoginPage() {
        header.openLoginPage();
        return initPage(getDriver(), LoginPageBase.class);
    }

    public boolean isUserLogined(String userName) {
        return header.isUserLogined(userName);
    }

    public Header getHeader() {
        return header;
    }

    public BrowsingHistoryPageBase openBrowsingHistory() {
        browsingHistoryLink.click();
        browsingHistoryLink.clickIfPresent();
        return initPage(getDriver(), BrowsingHistoryPageBase.class);
    }

    @Override
    public boolean isPageOpened() {
        return browsingHistoryLink.isPresent();
    }
}

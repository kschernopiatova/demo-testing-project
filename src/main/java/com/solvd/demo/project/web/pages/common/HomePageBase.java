package com.solvd.demo.project.web.pages.common;

import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import com.solvd.demo.project.web.components.desktop.Header;

public abstract class HomePageBase extends AbstractPage {

    @FindBy(id = "navbar")
    protected Header header;

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

    @Override
    public void open() {
        super.open();
    }

    public void open(boolean changeLoc) {
        super.open();
    }

    public Header getHeader() {
        return header;
    }
}

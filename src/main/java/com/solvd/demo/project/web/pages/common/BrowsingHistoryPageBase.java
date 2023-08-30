package com.solvd.demo.project.web.pages.common;

import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class BrowsingHistoryPageBase extends AbstractPage {

    public BrowsingHistoryPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract void disableHistory();

    public abstract void enableHistory();

    public abstract Integer getNumberOfViewedProducts();
}

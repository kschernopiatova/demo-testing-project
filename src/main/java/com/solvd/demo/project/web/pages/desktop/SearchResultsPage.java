package com.solvd.demo.project.web.pages.desktop;

import com.zebrunner.carina.utils.factory.DeviceType;
import org.openqa.selenium.WebDriver;
import com.solvd.demo.project.web.pages.common.SearchResultsPageBase;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = SearchResultsPageBase.class)
public class SearchResultsPage extends SearchResultsPageBase {

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }
}

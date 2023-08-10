package com.solvd.demo.project.web.pages.android;

import com.solvd.demo.project.enums.SortingOption;
import com.solvd.demo.project.web.components.android.AndroidFilterMenu;
import com.solvd.demo.project.web.components.desktop.FilterMenu;
import com.solvd.demo.project.web.pages.common.SearchResultsPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = SearchResultsPageBase.class)
public class SearchResultsPage extends SearchResultsPageBase {

    @FindBy(xpath = "//h4")
    private ExtendedWebElement productCategory;

    @FindBy(id = "s-all-filters")
    private ExtendedWebElement filterButton;

    @FindBy(id = "dropdown-content-s-all-filters")
    private AndroidFilterMenu filterMenu;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getProductCategory() {
        return productCategory.getText();
    }

    @Override
    public void chooseSortingOption(SortingOption sortingOption) {
        filterButton.click();
        filterMenu.chooseSortingOption(sortingOption);
    }

    @Override
    public FilterMenu getFilterMenu() {
        filterButton.click();
        return filterMenu;
    }
}

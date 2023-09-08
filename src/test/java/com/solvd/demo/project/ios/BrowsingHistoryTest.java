package com.solvd.demo.project.ios;

import com.nordstrom.automation.testng.LinkedListeners;
import com.solvd.demo.project.AmazonListener;
import com.solvd.demo.project.web.components.desktop.ProductCard;
import com.solvd.demo.project.web.pages.common.BrowsingHistoryPageBase;
import com.solvd.demo.project.web.pages.common.HomePageBase;
import com.solvd.demo.project.web.pages.common.SearchResultsPageBase;
import com.zebrunner.carina.core.IAbstractTest;
import org.testng.Assert;
import org.testng.annotations.Test;

@LinkedListeners(AmazonListener.class)
public class BrowsingHistoryTest implements IAbstractTest {

    @Test
    public void viewProductTest() {
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        BrowsingHistoryPageBase browsingHistoryPage = homePage.openBrowsingHistory();
        browsingHistoryPage.enableHistory();
        int expectedNumber = browsingHistoryPage.getNumberOfViewedProducts() + 1;
        SearchResultsPageBase searchResultsPage = initPage(getDriver(), SearchResultsPageBase.class);
        searchResultsPage.open();
        ProductCard product = searchResultsPage.getRandomProductCard();
        product.openProductPage().clickLogoButton();
        browsingHistoryPage = homePage.openBrowsingHistory();
        Assert.assertEquals(browsingHistoryPage.getNumberOfViewedProducts(), expectedNumber, "The number isn't correct!");
    }

    @Test
    public void disableHistoryTest() {
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        BrowsingHistoryPageBase browsingHistoryPage = homePage.openBrowsingHistory();
        int expectedNumber = browsingHistoryPage.getNumberOfViewedProducts();
        browsingHistoryPage.disableHistory();
        SearchResultsPageBase searchResultsPage = initPage(getDriver(), SearchResultsPageBase.class);
        searchResultsPage.open();
        ProductCard product = searchResultsPage.getRandomProductCard();
        product.openProductPage().clickLogoButton();
        homePage.openBrowsingHistory();
        Assert.assertEquals(browsingHistoryPage.getNumberOfViewedProducts(), expectedNumber, "The number isn't correct!");
    }
}

package com.solvd.demo.project;

import com.zebrunner.carina.core.IAbstractTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.solvd.demo.project.web.components.desktop.FilterMenu;
import com.solvd.demo.project.web.components.desktop.ProductCard;
import com.solvd.demo.project.web.pages.common.HomePageBase;
import com.solvd.demo.project.web.pages.common.SearchResultsPageBase;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FilterProductsTest implements IAbstractTest {

    private static final String ZIP_CODE = "10003";

    @Test
    public void filterRatingTest() {
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        homePage.getHeader().chooseUSLocation(ZIP_CODE);
        SearchResultsPageBase searchResultsPage = homePage.getHeader().openRandomSuggestedGoods();
        FilterMenu filterMenu = searchResultsPage.getFilterMenu();
        int ratingBound = 4;
        filterMenu.chooseRatingFilter(ratingBound);
        List<Double> ratings = searchResultsPage.getFoundProducts().stream()
                .map(ProductCard::getRating)
                .collect(Collectors.toList());
        ratings.forEach(rating -> Assert.assertTrue(rating >= (double) ratingBound,
                "The rating is lower than chosen one!"));
    }

    @Test
    public void filterPriceTest() {
        SoftAssert softAssert = new SoftAssert();
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        homePage.getHeader().chooseUSLocation(ZIP_CODE);
        SearchResultsPageBase searchResultsPage = homePage.getHeader().openRandomSuggestedGoods();
        FilterMenu filterMenu = searchResultsPage.getFilterMenu();
        double lowPrice = 20.0;
        double highPrice = 80.0;
        filterMenu.setPriceLimits(lowPrice, highPrice);
        List<Double> prices = searchResultsPage.getFoundProducts().stream()
                .map(ProductCard::getPrice)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        prices.forEach(price -> softAssert.assertTrue(price >= lowPrice,
                "The price is lower of the low bound!"));
        prices.forEach(price -> softAssert.assertTrue(price <= highPrice,
                "The price is higher of the high bound!"));
        softAssert.assertAll();
    }

    @Test
    public void filterPriceAndroidTest() {
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        homePage.getHeader().chooseUSLocation(ZIP_CODE);
        SearchResultsPageBase searchResultsPage = homePage.getHeader().openRandomSuggestedGoods();
        FilterMenu filterMenu = searchResultsPage.getFilterMenu();
        double lowPrice = 20.0;
        Double lowLimit = filterMenu.setLowPriceLimit(lowPrice);
        List<Double> prices = searchResultsPage.getFoundProducts().stream()
                .map(ProductCard::getPrice)
                .collect(Collectors.toList());
        prices.forEach(price -> Assert.assertTrue(price >= lowLimit,
                "The price is lower of the low bound!"));
    }

}

package com.solvd.demo.project.ios;

import com.nordstrom.automation.testng.LinkedListeners;
import com.solvd.demo.project.AmazonListener;
import com.solvd.demo.project.web.components.desktop.FilterMenu;
import com.solvd.demo.project.web.components.desktop.ProductCard;
import com.solvd.demo.project.web.pages.common.SearchResultsPageBase;
import com.zebrunner.carina.core.IAbstractTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

@LinkedListeners(AmazonListener.class)
public class IosFilterProductsTest implements IAbstractTest {

    @Test
    public void filterRatingIosTest() {
        SearchResultsPageBase searchResultsPage = initPage(getDriver(), SearchResultsPageBase.class);
        searchResultsPage.open();
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
    public void filterPriceIosTest() {
        SearchResultsPageBase searchResultsPage = initPage(getDriver(), SearchResultsPageBase.class);
        searchResultsPage.open();
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

package com.solvd.demo.project.ios;

import com.solvd.demo.project.enums.SortingOption;
import com.solvd.demo.project.web.components.desktop.ProductCard;
import com.solvd.demo.project.web.pages.common.HomePageBase;
import com.solvd.demo.project.web.pages.common.ProductPageBase;
import com.solvd.demo.project.web.pages.common.SearchResultsPageBase;
import com.zebrunner.carina.core.IAbstractTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class IosSearchProductTest implements IAbstractTest {

    @Test
    public void sortPriceIosTest() {
        SearchResultsPageBase searchResultsPage = initPage(getDriver(), SearchResultsPageBase.class);
        searchResultsPage.open();
        searchResultsPage.chooseSortingOption(SortingOption.PRICE_DESC);
        List<Double> prices = searchResultsPage.getFoundProducts().stream()
                .map(ProductCard::getPrice)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        for (int i = 0; i < prices.size() - 1; i++) {
            Assert.assertTrue(prices.get(i) >= prices.get(i+1), "The price isn't sorted descending!");
        }
    }

    @Test
    public void sortRatingIosTest() {
        SearchResultsPageBase searchResultsPage = initPage(getDriver(), SearchResultsPageBase.class);
        searchResultsPage.open();
        searchResultsPage.chooseSortingOption(SortingOption.RATING);
        List<Double> ratings = searchResultsPage.getFoundProducts().stream()
                .map(ProductCard::getRating)
                .collect(Collectors.toList());
        for (int i = 0; i < ratings.size() - 1; i++) {
            Assert.assertTrue(ratings.get(i) >= ratings.get(i+1), "The rating isn't sorted descending!");
        }
    }

    @Test
    public void openProductCardIOSTest() {
        SoftAssert softAssert = new SoftAssert();
        SearchResultsPageBase searchResultsPage = initPage(getDriver(), SearchResultsPageBase.class);
        searchResultsPage.open();
        ProductCard product = searchResultsPage.getFullProductCard();
        String expectedTitle = product.getProductTitle();
        Double expectedPrice = product.getPrice();
        Double expectedRating = product.getRating();
        ProductPageBase productPage = product.openProductPage();
        softAssert.assertEquals(expectedTitle, productPage.getProductTitle(),
                "The title isn't as expected!");
        softAssert.assertEquals(expectedPrice, productPage.getProductPrice(),
                "The price isn't as expected!");
        softAssert.assertEquals(expectedRating, productPage.getProductRating(),
                "The rating isn't as expected!");
        softAssert.assertAll();
    }

    @Test
    public void productLogoIosTest() {
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        SearchResultsPageBase searchResultsPage = initPage(getDriver(), SearchResultsPageBase.class);
        searchResultsPage.open();
        ProductCard product = searchResultsPage.getRandomProductCard();
        ProductPageBase productPage = product.openProductPage();
        productPage.clickLogoButton();
        Assert.assertTrue(homePage.isPageOpened(), "Home Page isn't opened!");
    }
}

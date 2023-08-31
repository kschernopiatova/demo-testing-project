package com.solvd.demo.project;

import com.zebrunner.carina.core.IAbstractTest;
import com.solvd.demo.project.enums.SortingOption;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.solvd.demo.project.web.components.desktop.ProductCard;
import com.solvd.demo.project.web.pages.common.HomePageBase;
import com.solvd.demo.project.web.pages.common.ProductPageBase;
import com.solvd.demo.project.web.pages.common.SearchResultsPageBase;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SearchProductTest implements IAbstractTest {

    private static final String ZIP_CODE = "10003";

    @Test
    public void sortPriceTest() {
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        homePage.getHeader().chooseUSLocation(ZIP_CODE);
        SearchResultsPageBase searchResultsPage = homePage.getHeader().openRandomSuggestedGoods();
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
    public void openProductCardTest() {
        SoftAssert softAssert = new SoftAssert();
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        homePage.getHeader().chooseUSLocation(ZIP_CODE);
        SearchResultsPageBase searchResultsPage = homePage.getHeader().openRandomSuggestedGoods();
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
}

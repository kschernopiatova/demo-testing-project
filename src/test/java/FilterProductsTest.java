import com.zebrunner.carina.core.IAbstractTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import web.components.desktop.FilterMenu;
import web.components.desktop.ProductCard;
import web.pages.common.HomePageBase;
import web.pages.common.SearchResultsPageBase;

import java.util.List;
import java.util.stream.Collectors;

public class FilterProductsTest implements IAbstractTest {

    @Test
    public void filterRatingTest() {
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
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
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        SearchResultsPageBase searchResultsPage = homePage.getHeader().openRandomSuggestedGoods();
        FilterMenu filterMenu = searchResultsPage.getFilterMenu();
        double lowPrice = 20.0;
        double highPrice = 80.0;
        filterMenu.setPriceLimits(lowPrice, highPrice);
        List<Double> prices = searchResultsPage.getFoundProducts().stream()
                .map(ProductCard::getPrice)
                .collect(Collectors.toList());
        prices.forEach(price -> Assert.assertTrue(price >= lowPrice,
                "The price is lower of the low bound!"));
        prices.forEach(price -> Assert.assertTrue(price <= highPrice,
                "The price is higher of the high bound!"));
    }

}

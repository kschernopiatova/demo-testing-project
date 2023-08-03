import com.zebrunner.carina.core.IAbstractTest;
import enums.SortingOption;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import web.components.desktop.ProductCard;
import web.pages.common.HomePageBase;
import web.pages.common.ProductPageBase;
import web.pages.common.SearchResultsPageBase;

import java.util.List;
import java.util.stream.Collectors;

public class SearchProductTest implements IAbstractTest {

    @Test
    public void sortPriceTest() {
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        SearchResultsPageBase searchResultsPage = homePage.getHeader().openRandomSuggestedGoods();
        searchResultsPage.chooseSortingOption(SortingOption.PRICE_DESC);
        List<Double> prices = searchResultsPage.getFoundProducts().stream()
                .map(ProductCard::getPrice)
                .collect(Collectors.toList());
        for (int i = 0; i < prices.size(); i++) {
            Assert.assertTrue(prices.get(i) > prices.get(i+1), "The price isn't sorted descending!");
        }
    }

    @Test
    public void openProductCardTest() {
        SoftAssert softAssert = new SoftAssert();
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        SearchResultsPageBase searchResultsPage = homePage.getHeader().openRandomSuggestedGoods();
        ProductCard product = searchResultsPage.getRandomProductCard();
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

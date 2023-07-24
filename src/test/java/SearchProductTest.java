import com.zebrunner.carina.core.IAbstractTest;
import enums.SortingOption;
import org.testng.Assert;
import org.testng.annotations.Test;
import web.components.desktop.ProductCard;
import web.pages.common.HomePageBase;
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
            Assert.assertTrue(prices.get(i) > prices.get(i+1));
        }
    }
}

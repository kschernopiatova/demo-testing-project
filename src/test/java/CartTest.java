import com.zebrunner.carina.core.IAbstractTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import web.components.desktop.CartSideBar;
import web.pages.common.HomePageBase;
import web.pages.common.ProductPageBase;
import web.pages.common.SearchResultsPageBase;

public class CartTest implements IAbstractTest {

    @Test
    public void addProductToCart() {
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        SearchResultsPageBase searchResultsPage = homePage.getHeader().openRandomSuggestedGoods();
        ProductPageBase productPage = searchResultsPage.getRandomProductCard().openProductPage();
        productPage.addProductToCart();
        Assert.assertTrue(productPage.isCartSidebarPresent(), "Sidebar isn't present!");
    }

    @Test
    public void addSeveralProductsToCart() {
        SoftAssert softAssert = new SoftAssert();
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        SearchResultsPageBase searchResultsPage = homePage.getHeader().openRandomSuggestedGoods();
        ProductPageBase productPage = searchResultsPage.getRandomProductCard().openProductPage();
        productPage.chooseRandomQuantity();
        Integer expectedQuantity = productPage.getChosenQuantity();
        Double productPrice = productPage.getProductPrice();
        productPage.addProductToCart();
        Assert.assertTrue(productPage.isCartSidebarPresent(), "Sidebar isn't present!");
        CartSideBar cartSideBar = productPage.getCartSideBar();
        softAssert.assertEquals(expectedQuantity, cartSideBar.getCartProductQuantity(),
                "The quantity doesn't match the expected!");
        softAssert.assertEquals(productPrice * expectedQuantity, cartSideBar.getSubtotalAmount(),
                "The price doesn't match the expected!");
        softAssert.assertAll();
    }

    @Test
    public void addDifferentProductsToCart() {
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        SearchResultsPageBase searchResultsPage = homePage.getHeader().openRandomSuggestedGoods();
        ProductPageBase productPage = searchResultsPage.getRandomProductCard().openProductPage();
        Double firstPrice = productPage.getProductPrice();
        productPage.addProductToCart();

        homePage.getHeader().openRandomSuggestedGoods();
        productPage = searchResultsPage.getRandomProductCard().openProductPage();
        Double secondPrice = productPage.getProductPrice();
        productPage.addProductToCart();

        Assert.assertTrue(productPage.isCartSidebarPresent(), "Sidebar isn't present!");
        CartSideBar cartSideBar = productPage.getCartSideBar();
        Assert.assertEquals(firstPrice + secondPrice, cartSideBar.getSubtotalAmount(),
                "The price doesn't match the expected!");
    }

    @Test
    public void addAndDeleteProduct() {
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        SearchResultsPageBase searchResultsPage = homePage.getHeader().openRandomSuggestedGoods();
        ProductPageBase productPage = searchResultsPage.getRandomProductCard().openProductPage();
        productPage.addProductToCart();

        CartSideBar cartSideBar = productPage.getCartSideBar();
        cartSideBar.deleteProductFromCart();
        Assert.assertEquals(cartSideBar.getSubtotalAmount(), 0.0, "The price isn't 0.00!");
    }
}

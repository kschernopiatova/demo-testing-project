package com.solvd.demo.project;

import com.solvd.demo.project.web.components.desktop.SideBarProduct;
import com.zebrunner.carina.core.IAbstractTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.solvd.demo.project.web.components.desktop.CartSideBar;
import com.solvd.demo.project.web.pages.common.HomePageBase;
import com.solvd.demo.project.web.pages.common.ProductPageBase;
import com.solvd.demo.project.web.pages.common.SearchResultsPageBase;

public class CartTest implements IAbstractTest {

    private static final String ZIP_CODE = "10003";

    @Test
    public void addProductToCart() {
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        homePage.getHeader().chooseUSLocation(ZIP_CODE);
        SearchResultsPageBase searchResultsPage = homePage.getHeader().openRandomSuggestedGoods();
        ProductPageBase productPage = searchResultsPage.getFullProductCard().openProductPage();
        productPage.addProductToCart();
        Assert.assertTrue(productPage.isCartSidebarPresent(), "Sidebar isn't present!");
    }

    @Test
    public void addSeveralProductsToCart() {
        SoftAssert softAssert = new SoftAssert();
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        homePage.getHeader().chooseUSLocation(ZIP_CODE);
        SearchResultsPageBase searchResultsPage = homePage.getHeader().openRandomSuggestedGoods();
        ProductPageBase productPage = searchResultsPage.getFullProductCard().openProductPage();
        productPage.chooseRandomQuantity();
        Integer expectedQuantity = productPage.getChosenQuantity();
        Double productPrice = productPage.getProductPrice();
        productPage.addProductToCart();
        Assert.assertTrue(productPage.isCartSidebarPresent(), "Sidebar isn't present!");
        CartSideBar cartSideBar = productPage.getCartSideBar();
        SideBarProduct addedProduct = cartSideBar.getFirstSideBarProduct();
        double expectedAmount = (double) Math.round(productPrice * expectedQuantity * 100) / 100;
        softAssert.assertEquals(expectedQuantity, addedProduct.getProductQuantity(),
                "The quantity doesn't match the expected!");
        softAssert.assertEquals(cartSideBar.getSubtotalAmount(), expectedAmount,
                "The price doesn't match the expected!");
        softAssert.assertAll();
    }

    @Test
    public void addDifferentProductsToCart() {
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        homePage.getHeader().chooseUSLocation(ZIP_CODE);
        SearchResultsPageBase searchResultsPage = homePage.getHeader().openRandomSuggestedGoods();
        ProductPageBase productPage = searchResultsPage.getFullProductCard().openProductPage();
        Double firstPrice = productPage.getProductPrice();
        productPage.addProductToCart();

        homePage.getHeader().openRandomSuggestedGoods();
        productPage = searchResultsPage.getFullProductCard().openProductPage();
        Double secondPrice = productPage.getProductPrice();
        productPage.addProductToCart();

        double expectedAmount = (double) Math.round((firstPrice + secondPrice) * 100) / 100;
        Assert.assertTrue(productPage.isCartSidebarPresent(), "Sidebar isn't present!");
        CartSideBar cartSideBar = productPage.getCartSideBar();
            Assert.assertEquals(cartSideBar.getSubtotalAmount(), expectedAmount,
                "The price doesn't match the expected!");
    }

    @Test
    public void addAndDeleteProduct() {
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        homePage.getHeader().chooseUSLocation(ZIP_CODE);
        SearchResultsPageBase searchResultsPage = homePage.getHeader().openRandomSuggestedGoods();
        ProductPageBase productPage = searchResultsPage.getFullProductCard().openProductPage();
        productPage.addProductToCart();

        CartSideBar cartSideBar = productPage.getCartSideBar();
        cartSideBar.getFirstSideBarProduct().deleteFromCart();
        Assert.assertEquals(cartSideBar.getSubtotalAmount(), 0.0, "The price isn't 0.00!");
    }
}

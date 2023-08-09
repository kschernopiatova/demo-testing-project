package com.solvd.demo.project;

import com.solvd.demo.project.web.components.desktop.CartProduct;
import com.zebrunner.carina.core.IAbstractTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.solvd.demo.project.web.pages.common.CartPageBase;
import com.solvd.demo.project.web.pages.common.HomePageBase;
import com.solvd.demo.project.web.pages.common.ProductPageBase;
import com.solvd.demo.project.web.pages.common.SearchResultsPageBase;

public class AndroidCartTest implements IAbstractTest {

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
        CartPageBase cartPage = homePage.getHeader().openCart();
        CartProduct addedProduct = cartPage.getFirstCartProduct();
        softAssert.assertEquals(addedProduct.getProductQuantity(), expectedQuantity,
                "The quantity doesn't match the expected!");
        softAssert.assertEquals(cartPage.getSubtotalAmount(), productPrice * expectedQuantity,
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

        CartPageBase cartPage = homePage.getHeader().openCart();
        Assert.assertEquals(cartPage.getSubtotalAmount(), firstPrice + secondPrice,
                "The price doesn't match the expected!");
    }

    @Test
    public void addAndDeleteProduct() {
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        SearchResultsPageBase searchResultsPage = homePage.getHeader().openRandomSuggestedGoods();
        ProductPageBase productPage = searchResultsPage.getRandomProductCard().openProductPage();
        productPage.addProductToCart();

        CartPageBase cartPage = homePage.getHeader().openCart();
        cartPage.getFirstCartProduct().deleteFromCart();
        cartPage.refresh();
        Assert.assertTrue(cartPage.isCartEmpty(), "The product isn't deleted!");
    }
}

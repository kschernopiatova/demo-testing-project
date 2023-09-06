package com.solvd.demo.project;

import com.solvd.demo.project.api.*;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import com.zebrunner.carina.core.IAbstractTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.*;

public class APITest implements IAbstractTest {

    @Test
    public void createTokenTest() {
        PostCreateToken api = new PostCreateToken();
        api.callAPIExpectSuccess();
        api.validateResponse();
    }

    @Test
    public void getBookingsTest() {
        GetBookings api = new GetBookings();
        api.callAPIExpectSuccess();
        api.validateResponseAgainstSchema("api/booking/_get/rs.schema");
    }

    @Test
    public void getBookingById() {
        GetBooking newApi = new GetBooking(getRandomBookingId());
        newApi.callAPIExpectSuccess();
        newApi.validateResponse();
    }

    @Test
    public void postBookingTest() {
        PostBooking postBooking = new PostBooking();
        Response response = postBooking.callAPIExpectSuccess();
        postBooking.validateResponse();
        String bookingId = response.getBody().jsonPath().getString("bookingid");
        GetBooking getBooking = new GetBooking(bookingId);
        getBooking.callAPIExpectSuccess();
    }

    @Test
    public void putBookingTest() {
        SoftAssert softAssert = new SoftAssert();
        String randomBookingId = getRandomBookingId();
        PutBooking api = new PutBooking(randomBookingId, getToken());
        api.callAPIExpectSuccess();
        api.validateResponse();
        Properties properties = api.getProperties();

        Response response = new GetBooking(randomBookingId).callAPIExpectSuccess();
        Set<Object> propertyNames = properties.keySet();
        for (Object propertyName : propertyNames) {
            String name = (String) propertyName;
            String changedProperty = response.getBody().jsonPath().getString(name);
            if (changedProperty == null)
                changedProperty = response.getBody().jsonPath().getString("bookingdates." + name);
            softAssert.assertEquals(changedProperty, properties.get(name), "The property value isn't changed!");
        }
        softAssert.assertAll();
    }

    @Test
    public void patchBookingTest() {
        SoftAssert softAssert = new SoftAssert();
        String randomBookingId = getRandomBookingId();
        PatchBooking api = new PatchBooking(randomBookingId, getToken());
        api.callAPIExpectSuccess();
        api.validateResponse();

        GetBooking getBooking = new GetBooking(randomBookingId);
        Response response = getBooking.callAPIExpectSuccess();
        String firstName = response.getBody().jsonPath().getString("firstname");
        String lastName = response.getBody().jsonPath().getString("lastname");
        softAssert.assertEquals(firstName, api.getProperties().get("firstName"), "The first name isn't changed!");
        softAssert.assertEquals(lastName, api.getProperties().get("lastName"), "The last name isn't changed!");
        softAssert.assertAll();
    }

    @Test
    public void deleteBookingTest() {
        String randomId = getRandomBookingId();
        DeleteBooking api = new DeleteBooking(randomId, getToken());
        api.callAPIExpectSuccess();
        GetBooking getBooking = new GetBooking(randomId);
        getBooking.callAPI();
        getBooking.expectResponseStatus(HttpResponseStatusType.NOT_FOUND_404);
    }

    public String getToken() {
        PostCreateToken api = new PostCreateToken();
        Response response = api.callAPIExpectSuccess();
        return response.getBody().jsonPath().getString("token");
    }

    public String getRandomBookingId() {
        GetBookings api = new GetBookings();
        Response response = api.callAPIExpectSuccess();
        List<Integer> bookingIds = response.getBody().jsonPath().getList("bookingid");
        return bookingIds.get(new Random().nextInt(bookingIds.size())).toString();
    }
}

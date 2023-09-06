package com.solvd.demo.project;

import com.solvd.demo.project.api.*;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import com.zebrunner.carina.core.IAbstractTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

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
        PutBooking api = new PutBooking(getRandomBookingId(), getToken());
        api.callAPIExpectSuccess();
        api.validateResponse();
    }

    @Test
    public void patchBookingTest() {
        PatchBooking api = new PatchBooking(getRandomBookingId(), getToken());
        api.callAPIExpectSuccess();
        api.validateResponse();
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

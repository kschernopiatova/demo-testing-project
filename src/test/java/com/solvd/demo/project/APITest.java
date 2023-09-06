package com.solvd.demo.project;

import com.solvd.demo.project.api.*;
import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.binding.TemplateFactory;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import com.zebrunner.carina.core.IAbstractTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.*;

public class APITest implements IAbstractTest {

    @Test
    public void createTokenTest() {
        TokenTemplate tokenTemplate = TemplateFactory.prepareTemplate(TokenTemplate.class);
        AbstractApiMethodV2 api = tokenTemplate.create();
        api.callAPIExpectSuccess();
        api.validateResponse();
    }

    @Test
    public void getBookingsTest() {
        BookingTemplate bookingTemplate = TemplateFactory.prepareTemplate(BookingTemplate.class);
        AbstractApiMethodV2 api = bookingTemplate.getAll();
        api.callAPIExpectSuccess();
        api.validateResponseAgainstSchema("api/booking/_get/rs.schema");
    }

    @Test
    public void getBookingById() {
        String bookingId = ApiService.getRandomBookingId();
        BookingTemplate bookingTemplate = TemplateFactory.prepareTemplate(BookingTemplate.class);
        AbstractApiMethodV2 api = bookingTemplate.getById(bookingId);
        api.callAPIExpectSuccess();
        api.validateResponse();
    }

    @Test
    public void postBookingTest() {
        BookingTemplate bookingTemplate = TemplateFactory.prepareTemplate(BookingTemplate.class);
        AbstractApiMethodV2 api = bookingTemplate.post();
        Response response = api.callAPIExpectSuccess();
        api.validateResponse();
        String bookingId = response.getBody().jsonPath().getString("bookingid");
        AbstractApiMethodV2 getBooking = bookingTemplate.getById(bookingId);
        getBooking.callAPIExpectSuccess();
    }

    @Test
    public void putBookingTest() {
        SoftAssert softAssert = new SoftAssert();
        String bookingId = ApiService.getRandomBookingId();
        BookingTemplate bookingTemplate = TemplateFactory.prepareTemplate(BookingTemplate.class);
        AbstractApiMethodV2 api = bookingTemplate.putById(bookingId);
        ApiService.addToken(api);
        api.callAPIExpectSuccess();
        api.validateResponse();
        Properties properties = api.getProperties();

        Response response = bookingTemplate.getById(bookingId).callAPIExpectSuccess();
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
        String bookingId = ApiService.getRandomBookingId();
        BookingTemplate bookingTemplate = TemplateFactory.prepareTemplate(BookingTemplate.class);
        AbstractApiMethodV2 api = bookingTemplate.patchById(bookingId);
        ApiService.addToken(api);
        api.callAPIExpectSuccess();
        api.validateResponse();

        Response response = bookingTemplate.getById(bookingId).callAPIExpectSuccess();
        String firstName = response.getBody().jsonPath().getString("firstname");
        String lastName = response.getBody().jsonPath().getString("lastname");
        softAssert.assertEquals(firstName, api.getProperties().get("firstName"), "The first name isn't changed!");
        softAssert.assertEquals(lastName, api.getProperties().get("lastName"), "The last name isn't changed!");
        softAssert.assertAll();
    }

    @Test
    public void deleteBookingTest() {
        String bookingId = ApiService.getRandomBookingId();
        BookingTemplate bookingTemplate = TemplateFactory.prepareTemplate(BookingTemplate.class);
        AbstractApiMethodV2 api = bookingTemplate.deleteById(bookingId);
        ApiService.addToken(api);
        api.callAPIExpectSuccess();
        AbstractApiMethodV2 getBooking = bookingTemplate.getById(bookingId);
        getBooking.callAPI();
        getBooking.expectResponseStatus(HttpResponseStatusType.NOT_FOUND_404);
    }
}

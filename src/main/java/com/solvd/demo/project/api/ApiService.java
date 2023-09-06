package com.solvd.demo.project.api;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.binding.TemplateFactory;
import io.restassured.response.Response;

import java.util.List;
import java.util.Random;

public class ApiService {

    public static void addToken(AbstractApiMethodV2 api) {
        api.addCookie("token", getToken());
    }

    public static String getToken() {
        TokenTemplate tokenTemplate = TemplateFactory.prepareTemplate(TokenTemplate.class);
        AbstractApiMethodV2 api = tokenTemplate.create();
        Response response = api.callAPIExpectSuccess();
        return response.getBody().jsonPath().getString("token");
    }

    public static String getRandomBookingId() {
        BookingTemplate bookingTemplate = TemplateFactory.prepareTemplate(BookingTemplate.class);
        AbstractApiMethodV2 api = bookingTemplate.getAll();
        Response response = api.callAPIExpectSuccess();
        List<Integer> bookingIds = response.getBody().jsonPath().getList("bookingid");
        return bookingIds.get(new Random().nextInt(bookingIds.size())).toString();
    }
}

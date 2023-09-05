package com.solvd.demo.project.enums;

import java.util.Random;

public enum SearchOptions {

    FOOTWEAR("men footwear"),
    HEADPHONES("headphones"),
    PENCIL_CASE("pencil case"),
    HOME("home");

    private String search;

    SearchOptions(String search) {
        this.search = search;
    }

    public String getSearch() {
        return search;
    }

    public static String getRandomSearch() {
        return SearchOptions.values()[new Random().nextInt(SearchOptions.values().length)].getSearch();
    }
}

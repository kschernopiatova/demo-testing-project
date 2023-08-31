package com.solvd.demo.project.enums;

import java.util.Random;

public enum SearchOptions {

    PENCIL_POUCH("pencil pouch"),
    HEADPHONES("headphones"),
    PENCIL_CASE("pencil case"),
    COMPOSITION_NOTEBOOK("composition notebook");

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

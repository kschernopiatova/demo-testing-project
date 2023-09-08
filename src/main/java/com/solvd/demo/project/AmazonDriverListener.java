package com.solvd.demo.project;

import com.zebrunner.carina.webdriver.listener.DriverListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class AmazonDriverListener extends DriverListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public AmazonDriverListener(WebDriver driver) {
        super(driver);
    }

    public void afterClick(WebElement element) {
        LOGGER.info("Clicking element " + element.getTagName());
    }

    @Override
    public void afterGet(WebDriver driver, String url) {
        LOGGER.info("Url was opened " + url);
    }
}

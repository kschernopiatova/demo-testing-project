package com.solvd.demo.project;

import com.zebrunner.carina.webdriver.IDriverPool;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestListener;

import java.lang.invoke.MethodHandles;

public class DriverListener implements ITestListener, WebDriverListener, IDriverPool {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public void afterClick(WebElement element) {
        LOGGER.info("Clicking element " + element.getTagName());
    }

    @Override
    public void afterGet(WebDriver driver, String url) {
        LOGGER.info("Url was opened " + url);
    }
}

package com.solvd.demo.project;

import com.zebrunner.carina.core.IAbstractTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.testng.annotations.BeforeMethod;

public class AbstractTest implements IAbstractTest {

    protected WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {
        WebDriverListener listener = new DriverListener();
        this.driver = new EventFiringDecorator(listener).decorate(getDriver());
    }
}

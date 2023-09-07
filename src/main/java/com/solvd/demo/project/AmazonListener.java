package com.solvd.demo.project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.lang.invoke.MethodHandles;

public class AmazonListener implements ITestListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void onTestStart(ITestResult result) {
        LOGGER.info("Amazon Test Started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LOGGER.info("Amazon Test passed successfully");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LOGGER.error("Amazon Test failed!");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LOGGER.warn("Amazon Test skipped!");
    }
}

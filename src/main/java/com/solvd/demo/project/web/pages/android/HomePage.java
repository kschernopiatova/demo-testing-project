package com.solvd.demo.project.web.pages.android;

import com.zebrunner.carina.utils.factory.DeviceType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import com.solvd.demo.project.web.components.android.AndroidHeader;
import com.solvd.demo.project.web.pages.common.HomePageBase;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = HomePageBase.class)
public class HomePage extends HomePageBase {

    @FindBy(id = "navbar")
    private AndroidHeader header;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public AndroidHeader getHeader() {
        return header;
    }
}

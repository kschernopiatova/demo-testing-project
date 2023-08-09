package com.solvd.demo.project.web.pages.desktop;

import com.solvd.demo.project.web.pages.common.HomePageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import org.openqa.selenium.WebDriver;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = HomePageBase.class)
public class HomePage extends HomePageBase {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        super.open();
        header.chooseUSLocation("10003");
    }

    public void open(boolean changeLoc) {
        super.open();
        if (changeLoc)
            header.chooseUSLocation("10003");
    }
}
